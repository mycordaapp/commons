package mycorda.app.types

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.sameInstance
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException

class MapOfAnyTests {

    @Test
    fun `should return MapOfAny`() {
        val data = mapOf("name" to "Bob", "age" to 21)
        assertThat(data.toMapOfAny(), sameInstance(data))
    }

    @Test
    fun `should not return MapOfAny if has null keys`() {
        val data = mapOf("name" to "Bob", null to 21)
        assertThrows<RuntimeException> { data.toMapOfAny() }
    }

    @Test
    fun `should not return MapOfAny if has non string keys`() {
        val data = mapOf("name" to "Bob", 21 to "age")
        assertThrows<RuntimeException> { data.toMapOfAny() }
    }
}