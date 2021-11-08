package mycorda.app.chaos

/**
 * Code used in the docs
 */

class ChaosDocExamples {

    fun `example1`() {
        val chaos = Chaos(
            listOf(
                // average delay of 1 "platform" tick (tuned to the basic tick rate on the OS/Hardware)
                DelayUptoNTicks(2),
                // fail statistically 1 in 10 timed
                FailNPercent(10)

            )
        )
        val testDouble = TestDouble(chaos)

        // calls to foo method now have the injected chaotic behaviors
        testDouble.foo()
    }

}

class TestDouble(private val chaos: Chaos) {
    fun foo(): String {
        chaos.chaos()
        return "bar"
    }
}


class TestDouble2(private val chaos: Chaos) {
    fun foo(): String {
        chaos.chaos("errors")
        chaos.chaos("delays")
        return "bar"
    }
}


