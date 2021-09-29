abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "(7|8)[0-9]+$".toRegex()

        if (value != null) {
            if (!value.matches(regex))
                return listOf(ErrorCode.INVALID_PHONE)
        }

        if(value.isNullOrEmpty())
            return listOf(ErrorCode.NULLOREMPTY_VALUE)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LENGTH)

        return listOf()
    }
}

class FirstLastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[аА-яЯ]+$".toRegex()

        if (value != null) {
            if (!value.matches(regex))
                return listOf(ErrorCode.INVALID_FIRSTLASTNAME)
        }

        if(value.isNullOrEmpty())
            return listOf(ErrorCode.NULLOREMPTY_VALUE)

        if (value.length > 16)
            return listOf(ErrorCode.INVALID_LENGTH)

        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "^[aA-zZ0-9]+@[aA-zZ]+\\.[aA-zZ]+$".toRegex()

        if (value != null) {
            if (!value.matches(regex))
                return listOf(ErrorCode.INVALID_EMAIL)
        }

        if(value.isNullOrEmpty())
            return listOf(ErrorCode.NULLOREMPTY_VALUE)

        if (value.length > 32)
            return listOf(ErrorCode.INVALID_LENGTH)

        return listOf()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[0-9]+".toRegex()

        if (value != null) {
            if (!value.matches(regex))
                return listOf(ErrorCode.INVALID_SNILS)
        }

        if(value.isNullOrEmpty())
            return listOf(ErrorCode.NULLOREMPTY_VALUE)

        if (value.length != 11)
            return listOf(ErrorCode.INVALID_LENGTH)

        val snils = (value.dropLast(2)).map { it.toString().toInt() }.toIntArray() //CНИЛС без 2 цифр
        val controlNumber = value.takeLast(2).toInt() //последние 2 цифры

        val calculateControlNumber = calculateControlNumber(snils)

        if (calculateControlNumber != controlNumber)
            return listOf(ErrorCode.INVALID_SNILS)

        return listOf()
    }

    private fun calculateControlNumber(snils: IntArray): Int {
        var controlNumber = 0
        var index = 9

        for (digit in snils) {
            controlNumber += digit * index 
            index--
        }

        while (controlNumber >= 101)
            controlNumber %= 101

        if (controlNumber == 100)
            controlNumber = 0

        return controlNumber
    }
}
