package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "no middle name",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "no city",
    val specialization: String = "no specialization",
    val prevEducation: String? = "no previous education",
)
