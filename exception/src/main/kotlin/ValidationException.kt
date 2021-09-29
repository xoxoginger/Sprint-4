class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    NULLOREMPTY_VALUE(101, "Значение равно null или пусто"),
    INVALID_PHONE(102, "Номер телефона должен состоять из цифр и начинаться на 7 или 8"),
    INVALID_FIRSTLASTNAME(103, "Имя и фамилия должны быть написаны на кириллице"),
    INVALID_EMAIL(104, "Email должен быть написан на латинице"),
    INVALID_SNILS(105, "СНИЛС должен состоять только из цифр"),
    INVALID_LENGTH(106, "Недопустимая длина значения")
}
