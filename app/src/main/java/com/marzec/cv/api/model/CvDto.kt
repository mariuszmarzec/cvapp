package com.marzec.cv.api.model

import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Education
import com.marzec.cv.domain.model.Experience
import com.marzec.cv.domain.model.Position

data class CvDto(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val imageUrl: String,
    val experience: List<ExperienceDto>,
    val education: List<EducationDto>,
    val skills: List<String>
)

data class ExperienceDto(
    val imageUrl: String,
    val company: String,
    val location: String,
    val positions: List<PositionDto>
)

data class EducationDto(
    val imageUrl: String,
    val school: String,
    val degree: String,
    val fieldOfStudy: String,
    val startYear: String,
    val endYear: String
)

data class PositionDto(
    val position: String,
    val start: String,
    val end: String?,
    val responsibility: String
)

fun CvDto.toDomain() = Cv(
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    email = email,
    imageUrl = imageUrl,
    experience = experience.map { it.toDomain() },
    education = education.map { it.toDomain() },
    skills = skills
)

fun ExperienceDto.toDomain() = Experience(
    imageUrl = imageUrl,
    company = company,
    location = location,
    positions = positions.map { it.toDomain() }
)

fun EducationDto.toDomain() = Education(
    imageUrl = imageUrl,
    school = school,
    degree = degree,
    fieldOfStudy = fieldOfStudy,
    startYear = startYear,
    endYear = endYear
)

fun PositionDto.toDomain() = Position(
    position = position,
    start = start,
    end = end,
    responsibility = responsibility
)
