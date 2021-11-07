package mycorda.app.chaos

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.greaterThan
import com.natpryce.hamkrest.lessThan
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.lang.Exception

class ChaosTest {

    @Test
    fun `it should fail N percent of the time`() {
        val chaos = Chaos(listOf(FailNPercent(10)))
        try {
            // statistically, no chance of passing 100 times
            (1..100).forEach { _ -> chaos.chaos() }
            fail("Should have thrown an exception")
        } catch (ex: Exception) {
            assertThat(ex.message, equalTo("unlucky, punk"))
        }
    }

    @Test
    fun `it should pass 100 minus N percent of the time`() {
        val chaos = Chaos(listOf(FailNPercent(10)))
        val attempts = 1000
        val variance = 2.0
        var passCount = 0
        var failCount = 0
        // run enough to get some good stats
        (1..attempts).forEach {
            try {
                chaos.chaos()
                passCount++
            } catch (ex: Exception) {
                failCount++
            }
        }
        val passPercent = (passCount * 100.0) / attempts
        val failPercent = (failCount * 100.0) / attempts
        println(passPercent)
        println(failPercent)

        // are these statistically sensible results?
        assertThat(passPercent, greaterThan(90 - variance))
        assertThat(passPercent, lessThan(90 + variance))
        assertThat(failPercent, greaterThan(10 - variance))
        assertThat(failPercent, lessThan(10 + variance))
    }

}