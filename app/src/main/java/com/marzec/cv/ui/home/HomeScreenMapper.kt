package com.marzec.cv.ui.home

import androidx.annotation.StringRes
import com.marzec.cv.R
import com.marzec.cv.domain.model.Cv
import com.marzec.cv.common.Image
import com.marzec.cv.common.ResourceImage
import com.marzec.cv.common.StringProvider
import com.marzec.cv.common.UrlImage
import com.marzec.cv.domain.model.Education
import com.marzec.cv.domain.model.Experience
import com.marzec.cv.views.delegates.SectionHeaderDelegate
import com.marzec.cv.views.delegates.TextRowWithImageDelegate
import com.marzec.cv.views.model.ListItemView
import java.lang.IllegalArgumentException
import java.lang.StringBuilder
import javax.inject.Inject

interface HomeScreenMapper {

    fun mapItems(cv: Cv): HomeScreenContent
}

class HomeScreenMapperImpl @Inject constructor(
    private val stringProvider: StringProvider
) : HomeScreenMapper {
    override fun mapItems(cv: Cv): HomeScreenContent {
        return HomeScreenContent(createHeaderModel(cv), createContent(cv))
    }

    private fun createContent(cv: Cv): List<ListItemView> {
        val items = mutableListOf<ListItemView>()

        items.apply {
            add(createHeaderModel(R.string.section_header_experience, showTopDivider = false))
            addAll(createExperienceSection(cv.experience))
            add(createHeaderModel(R.string.section_header_education, showTopDivider = true))
            addAll(createEducationSection(cv.education))
            add(createHeaderModel(R.string.section_header_skills, showTopDivider = true))
            add(createSkillsModel(cv.skills))
        }
        return items
    }

    private fun createExperienceSection(experience: List<Experience>): List<ListItemView> {
        return experience.mapIndexed { index, company ->
            when {
                company.positions.isEmpty() -> {
                    throw IllegalArgumentException("Position has to have at least one element")
                }
                company.positions.size == 1 -> {
                    val position = company.positions.first()
                    listOf(
                        TextRowWithImageDelegate.Model(
                            image = UrlImage(company.imageUrl),
                            title = company.company,
                            subtitle = position.position,
                            showTopDivider = index > 0,
                            text = StringBuilder()
                                .append(position.start)
                                .append(" - ")
                                .append(
                                    position.end
                                        ?: stringProvider.getString(R.string.time_period_present)
                                )
                                .append("\n")
                                .append(company.location)
                                .append("\n")
                                .append(position.responsibility)
                                .toString()
                        )
                    )
                }
                else -> {
                    listOf(
                        TextRowWithImageDelegate.Model(
                            image = UrlImage(company.imageUrl),
                            title = company.company,
                            subtitle = company.location,
                            showTopDivider = index > 0
                        )
                    ) + company.positions.mapIndexed { positionIndex, position ->
                        TextRowWithImageDelegate.Model(
                            image = ResourceImage(R.drawable.ic_point_black_24dp),
                            title = position.position,
                            subtitle =  StringBuilder()
                                .append(position.start)
                                .append(" - ")
                                .append(position.end ?: stringProvider.getString(R.string.time_period_present))
                                .toString(),
                            text = position.responsibility,
                            showTopDivider = positionIndex > 0
                        )
                    }
                }
            }
        }.flatten()
    }

    private fun createEducationSection(education: List<Education>): List<ListItemView> {
        return education.mapIndexed { index, school ->
            TextRowWithImageDelegate.Model(
                image = UrlImage(school.imageUrl),
                title = school.school,
                subtitle = school.degree,
                text = StringBuilder().append(school.fieldOfStudy).append("\n")
                    .append(school.startYear).append(" - ").append(school.endYear)
                    .toString(),
                showTopDivider = index > 0
            )
        }
    }

    private fun createSkillsModel(skills: List<String>): ListItemView {
        return SectionHeaderDelegate.Model(
            title = skills.joinToString(" \u2022 "),
            textColorResId = R.color.black_light,
            textSizeResId = R.dimen.default_padding,
            center = true,
            bold = false,
            showTopDivider = false
        )

    }

    private fun createHeaderModel(
        @StringRes titleResId: Int,
        showTopDivider: Boolean
    ): ListItemView {
        return SectionHeaderDelegate.Model(
            stringProvider.getString(titleResId),
            textColorResId = R.color.black,
            textSizeResId = R.dimen.xlarge_text_size,
            bold = true,
            showTopDivider = showTopDivider
        )
    }

    private fun createHeaderModel(cv: Cv): HeaderModel {
        return HeaderModel(
            UrlImage(cv.imageUrl),
            "${cv.firstName} ${cv.lastName}",
            cv.email,
            cv.phone
        )
    }


}

data class HomeScreenContent(
    val header: HeaderModel,
    val viewItems: List<ListItemView>
)

data class HeaderModel(
    val image: Image,
    val fulName: String,
    val email: String,
    val phoneNumber: String
)