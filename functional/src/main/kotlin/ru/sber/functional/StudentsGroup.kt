package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    init {
        students = listOf(
            Student(firstName = "Hulia", lastName = "Vysotina", averageRate = 84.7),
            Student(firstName = "Rick", lastName = "Sanchez", age = 74, averageRate = 93.3),
            Student(firstName = "Morty", lastName = "Smith", averageRate = 14.6),
            Student(firstName = "BoJack", lastName = "Horseman", specialization = "actor", averageRate = 59.0),
            Student(firstName = "Maeve", lastName = "Wiley", city = "Melbrook", averageRate = 86.3),
            Student(firstName = "Otis", lastName = "Milborn", city = "Melbrook", averageRate = 99.9)

        )
    }
    fun filterByPredicate(pred: (Student) -> Boolean): List<Student> {
        return students.filter(pred)
    }
}
