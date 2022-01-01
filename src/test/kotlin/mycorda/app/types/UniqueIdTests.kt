package mycorda.app.types

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException

class UniqueIdTests {

    @Test
    fun `should throw exception if too long`() {
        assertDoesNotThrow { UniqueId.fromString("a".repeat(64)) }
        assertThrows<RuntimeException> { UniqueId.fromString("a".repeat(65)) }
    }

    @Test
    fun `should throw exception if empty`() {
        assertThrows<RuntimeException> { UniqueId.fromString("") }
        assertDoesNotThrow { UniqueId.fromString("a") }
    }

    @Test
    fun `should allow all supported characters`() {
        assertDoesNotThrow { UniqueId.fromString("abcdefghijklmnopqrstuvwyyz") }
        assertDoesNotThrow { UniqueId.fromString("abcdefghijklmnopqrstuvwyyz".toUpperCase()) }
        assertDoesNotThrow { UniqueId.fromString("1234567890") }
        assertDoesNotThrow { UniqueId.fromString("-_:") }
    }

    @Test
    fun `should fail for unsupported characters`() {
        // TODO - a better way of generating sets of invalid characters
        val unsupported = "!@£$%^&*()+=;|\\/?><.,Ö"
        unsupported.toCharArray().forEach {
            assertThrows<RuntimeException> { UniqueId.fromString(it.toString()) }
        }
    }
}