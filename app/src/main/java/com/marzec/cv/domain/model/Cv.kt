package com.marzec.cv.domain.model

data class Cv(
    val firstName: String,
    val lastName: String,
    val phone: String,
    val email: String,
    val imageUrl: String,
    val experience: List<Experience>,
    val education: List<Education>,
    val skills: List<String>
)

data class Experience(
    val imageUrl: String,
    val company: String,
    val location: String,
    val positions: List<Position>
)

data class Education(
    val imageUrl: String,
    val school: String,
    val degree: String,
    val fieldOfStudy: String,
    val startYear: String,
    val endYear: String
)

data class Position(
    val position: String,
    val start: String,
    val end: String?,
    val responsibility: String
)
