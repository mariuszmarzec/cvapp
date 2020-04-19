package com.marzec.cv.api

import com.marzec.cv.api.model.*

fun stubCvDto(
    firstName: String = "",
    lastName: String = "",
    phone: String = "",
    email: String = "",
    imageUrl: String = "",
    experience: List<ExperienceDto> = emptyList(),
    education: List<EducationDto> = emptyList(),
    skills: List<String> = emptyList()
) = CvDto(
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    imageUrl = imageUrl,
    experience = experience,
    education = education,
    skills = skills
)

fun stubExperienceDto(
    imageUrl: String = "",
    company: String = "",
    location: String = "",
    positions: List<PositionDto>
) = ExperienceDto(
    imageUrl = imageUrl,
    company = company,
    location = location,
    positions = positions
)

fun stubEducationDto(
    imageUrl: String = "",
    school: String = "",
    degree: String = "",
    fieldOfStudy: String = "",
    startYear: String = "",
    endYear: String
) = EducationDto(
    imageUrl = imageUrl,
    school = school,
    degree = degree,
    fieldOfStudy = fieldOfStudy,
    startYear = startYear,
    endYear = endYear
)

fun stubPositionDto(
    position: String = "",
    start: String = "",
    end: String? = "",
    responsibility: String
) = PositionDto(
    position = position, start = start, end = end, responsibility = responsibility
)