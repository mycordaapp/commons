package mycorda.app.helpers


import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.junit.Test


class StringExtensionSpec {

    @Test
    fun `should create random strings`() {
        assertThat(String.random().length, equalTo(6)) { "default length is 6 characters" }
        assertThat(String.random(99).length, equalTo(99)) { "random string can be any length" }
        (1..1000).forEach {
            assertThat(String.random(), !equalTo(String.random())) { "two random strings are not the same!" }
        }
        (1..100).forEach {
            val random = String.random()
            assertThat(random.toLowerCase(), equalTo(random)) { "random strings always in lowercase" }
        }
    }

}