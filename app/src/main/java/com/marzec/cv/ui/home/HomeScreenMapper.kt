package com.marzec.cv.ui.home

import androidx.annotation.StringRes
import com.marzec.cv.R
import com.marzec.cv.domain.model.Cv
import com.marzec.cv.common.Image
import com.marzec.cv.common.StringProvider
import com.marzec.cv.common.UrlImage
import com.marzec.cv.views.delegates.SectionHeaderDelegate
import com.marzec.cv.views.model.ListItemView
import javax.inject.Inject

interface HomeScreenMapper {

    fun mapItems(cv: Cv): HomeScreenContent
}
class HomeScreenMapperImpl @Inject constructor(
    private val stringProvider: StringProvider
): HomeScreenMapper {
    override fun mapItems(cv: Cv): HomeScreenContent {
        return HomeScreenContent(createHeaderModel(cv), createContent(cv))
    }

    private fun createContent(cv: Cv): MutableList<ListItemView> {
        val items = mutableListOf<ListItemView>()

        items += createSectionModel(R.string.section_header_expierence, showTopDivider = false)
        items += createSectionModel(R.string.section_header_education, showTopDivider = true)
        items += createSectionModel(R.string.section_header_skills, showTopDivider = true)
        items += createSkillsModel(cv.skills)
        return items
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

    private fun createSectionModel(@StringRes titleResId: Int, showTopDivider: Boolean): SectionHeaderDelegate.Model {
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
    val viewItems: MutableList<ListItemView>
)

data class HeaderModel(
    val image: Image,
    val fulName: String,
    val email: String,
    val phoneNumber: String
)