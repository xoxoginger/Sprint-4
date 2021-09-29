import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый формат номера телефона") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE)
        //assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        //assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - first name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_first_name.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый формат имени") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRSTLASTNAME)
        //assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        //assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - last name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_last_name.json")
        val exception = assertFailsWith<ValidationException>("Неверный формат фамилии") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRSTLASTNAME)
        //assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        //assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - email validation error`() {
        val client = getClientFromJson("fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый формат email-a") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_EMAIL)
        //assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        //assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый СНИЛС") {
            clientService.saveClient(client)
        }
        //assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)
        //assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH)
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("В полях содержатся ошибки") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE)
        assertEquals(exception.errorCode[1], ErrorCode.NULLOREMPTY_VALUE)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
        //assertEquals(exception.errorCode[5], ErrorCode.INVALID_FIRSTLASTNAME)
    }



    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
