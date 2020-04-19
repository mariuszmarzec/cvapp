package com.marzec.cv.ui.home

import com.marzec.cv.*
import com.marzec.cv.common.ResourceImage
import com.marzec.cv.common.UrlImage
import com.marzec.cv.views.delegates.SectionHeaderDelegate
import com.marzec.cv.views.delegates.TextRowWithImageDelegate
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import java.lang.StringBuilder

internal class HomeScreenMapperImplTest {

    private val stringProvider = MockStringProvider
    private lateinit var mapper: HomeScreenMapperImpl

    @BeforeEach
    fun setUp() {
        mapper = HomeScreenMapperImpl(stringProvider)
    }

    @Test
    fun `mapItems | create header`() {
        val cv = stubCv(
            imageUrl = "image_url",
            firstName = "first",
            lastName = "last",
            phone = "phone",
            email = "email"
        )
        val header = mapper.mapItems(cv).header

        assertEquals(
            stubHeaderModel(
                image = UrlImage(url = "image_url"),
                fulName = "first last",
                email = "email",
                phoneNumber = "phone"
            ),
            header
        )
    }

    @Test
    fun `mapItems | create content`() {
        val cv = stubCv(
            imageUrl = "image_url",
            firstName = "first",
            lastName = "last",
            phone = "phone",
            email = "email",
            experience = listOf(
                stubExperience(
                    imageUrl = "imageUrl",
                    company = "company",
                    location = "location",
                    positions = listOf(
                        stubPosition(
                            position = "position",
                            start = "start",
                            end = null,
                            responsibility = "responsibility"
                        )
                    )
                ),
                stubExperience(
                    imageUrl = "imageUrl_2",
                    company = "company_2",
                    location = "location_2",
                    positions = listOf(
                        stubPosition(
                            position = "position_2",
                            start = "start_2",
                            end = "end_2",
                            responsibility = "responsibility_2"
                        ),
                        stubPosition(
                            position = "position_3",
                            start = "start_3",
                            end = "end_3",
                            responsibility = "responsibility_3"
                        )                    )
                )
            ),
            education = listOf(
                stubEducation(
                    imageUrl = "imageUrl",
                    school = "school",
                    degree = "degree",
                    fieldOfStudy = "fieldOfStudy",
                    startYear = "startYear",
                    endYear = "endYear"
                )
            ),
            skills = listOf("skill", "skill2")
        )

        val expected = listOf(
            SectionHeaderDelegate.Model(
                stringProvider.getString(R.string.section_header_expierence),
                textColorResId = R.color.black,
                textSizeResId = R.dimen.xlarge_text_size,
                bold = true,
                showTopDivider = false
            ),
            TextRowWithImageDelegate.Model(
                image = UrlImage("imageUrl"),
                title = "company",
                subtitle = "position",
                text = "start - ${stringProvider.getString(R.string.time_period_present)}\nlocation\nresponsibility",
                showTopDivider = false
            ),
            TextRowWithImageDelegate.Model(
                image = UrlImage("imageUrl_2"),
                title = "company_2",
                subtitle = "location_2",
                showTopDivider = true
            ),
            TextRowWithImageDelegate.Model(
                image = ResourceImage(R.drawable.ic_point_black_24dp),
                title = "position_2",
                subtitle =  "start_2 - end_2",
                text = "responsibility_2",
                showTopDivider = false
            ),
            TextRowWithImageDelegate.Model(
                image = ResourceImage(R.drawable.ic_point_black_24dp),
                title = "position_3",
                subtitle =  "start_3 - end_3",
                text = "responsibility_3",
                showTopDivider = true
            ),
            SectionHeaderDelegate.Model(
                stringProvider.getString(R.string.section_header_education),
                textColorResId = R.color.black,
                textSizeResId = R.dimen.xlarge_text_size,
                bold = true,
                showTopDivider = true
            ),
            TextRowWithImageDelegate.Model(
                image = UrlImage("imageUrl"),
                title = "school",
                subtitle = "degree",
                text = "fieldOfStudy\nstartYear - endYear",
                showTopDivider = false
            ),
            SectionHeaderDelegate.Model(
                stringProvider.getString(R.string.section_header_skills),
                textColorResId = R.color.black,
                textSizeResId = R.dimen.xlarge_text_size,
                bold = true,
                showTopDivider = true
            ),
            SectionHeaderDelegate.Model(
                title = "skill \u2022 skill2",
                textColorResId = R.color.black_light,
                textSizeResId = R.dimen.default_padding,
                center = true,
                bold = false,
                showTopDivider = false
            )
        )

        val content = mapper.mapItems(cv).viewItems

        assertEquals(
            expected,
            content
        )
    }
}