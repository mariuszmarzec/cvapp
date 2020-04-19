package com.marzec.cv

import com.marzec.cv.domain.model.*
import com.marzec.cv.ui.home.HeaderModel
import com.marzec.cv.ui.home.HomeScreenContent
import com.marzec.cv.views.model.Image
import com.marzec.cv.views.model.ListItemView
import com.marzec.cv.views.model.UrlImage

fun stubCv(
    firstName: String = "",
    lastName: String = "",
    phone: String = "",
    email: String = "",
    imageUrl: String = "",
    experience: List<Experience> = emptyList(),
    education: List<Education> = emptyList(),
    skills: List<String> = emptyList()
) = Cv(
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    imageUrl = imageUrl,
    experience = experience,
    education = education,
    skills = skills
)

fun stubExperience(
    imageUrl: String = "",
    company: String = "",
    location: String = "",
    positions: List<Position> = emptyList()
) = Experience(
    imageUrl = imageUrl,
    company = company,
    location = location,
    positions = positions
)

fun stubEducation(
    imageUrl: String = "",
    school: String = "",
    degree: String = "",
    fieldOfStudy: String = "",
    startYear: String = "",
    endYear: String = ""
) = Education(
    imageUrl = imageUrl,
    school = school,
    degree = degree,
    fieldOfStudy = fieldOfStudy,
    startYear = startYear,
    endYear = endYear
)

fun stubPosition(
    position: String = "",
    start: String = "",
    end: String? = "",
    responsibility: String
) = Position(
    position = position, start = start, end = end, responsibility = responsibility
)

fun stubHomeScreenContent(
    header: HeaderModel = stubHeaderModel(),
    viewItems: List<ListItemView> = emptyList()
) = HomeScreenContent(
    header,
    viewItems
)

fun stubHeaderModel(
    image: Image = UrlImage(""),
    fulName: String = "",
    email: String = "",
    phoneNumber: String = ""
): HeaderModel {
    return HeaderModel(
        image = image,
        fulName = fulName,
        email = email,
        phoneNumber = phoneNumber
    )
}
