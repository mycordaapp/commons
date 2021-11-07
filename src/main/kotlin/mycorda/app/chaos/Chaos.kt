package mycorda.app.chaos

import mycorda.app.clock.PlatformTimer
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.random.Random

/**
 * Some type of chaotic behaviour
 */
interface Chaotic {
    fun `go ahead make my day`()
}

class FailNPercent(
    private val failurePercentage: Int = 10,
    private val exception: Exception = RuntimeException("unlucky, punk"),
    private val random: Random = Random
) : Chaotic {
    override fun `go ahead make my day`() {
        if (random.nextInt(100) < failurePercentage) throw exception
    }
}

class DelayUptoNTicks(
    private val maxTicks: Int = 5,
    private val random: Random = Random
) : Chaotic {
    override fun `go ahead make my day`() {
        val ticks = random.nextInt(maxTicks)
        PlatformTimer.sleepForTicks(ticks)
    }
}


class Chaos(private val chaotics: List<Chaotic> = emptyList()) {
    fun chaos() {
        chaotics.forEach { it.`go ahead make my day`() }
    }
}

fun main() {

    val chaos1 = Chaos(listOf(FailNPercent()))
    try {
        // statistically, no chance of passing 100 times
        (1..100).forEach {
            println("try #${it}")
            chaos1.chaos()
        }
    } catch (ex: Exception) {
        assert(ex.message == "unlucky!")
    }


    val startTime = System.currentTimeMillis()
    val chaos2 = Chaos(listOf(DelayUptoNTicks()))
    (1..10).forEach { _ -> chaos2.chaos() }
    val endTime = System.currentTimeMillis()

    println("results!!")
    println(endTime - startTime)
    println((endTime - startTime) / PlatformTimer.clockTick())


    // must have at least 1 tick
    assert((startTime + PlatformTimer.clockTick()) < endTime)
    // but shouldn;t be over this
    assert(startTime + (PlatformTimer.clockTick() * 5L) > endTime)


    println("aa")


}