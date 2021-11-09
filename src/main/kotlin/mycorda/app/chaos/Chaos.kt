package mycorda.app.chaos

import mycorda.app.clock.PlatformTimer
import java.lang.Exception
import java.lang.RuntimeException
import kotlin.random.Random

/**
 * Some type of chaotic behaviour
 */
interface Chaotic {
    // are you lucky or not ?
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

class AlwaysFail(private val exception: Exception = RuntimeException("unlucky, punk")) : Chaotic {
    override fun `go ahead make my day`() {
        throw exception
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

class Noop : Chaotic {
    override fun `go ahead make my day`() {}
}

class Chaos(private val chaotics: Map<String, List<Chaotic>>, private val ignoreWarnings: Boolean = false) {
    constructor(chaotics: List<Chaotic> = emptyList()) : this(mapOf("any" to chaotics))
    constructor(chaotic: Chaotic) : this(listOf(chaotic))
    constructor() : this(Noop())

    fun chaos(chaosType: String = "any") {
        val chaotics = this.chaotics[chaosType]
        if (chaotics != null) {
            chaotics.forEach { it.`go ahead make my day`() }
        } else {
            if (!ignoreWarnings) println("warning, no chaotics found for chaosType: $chaosType")
        }
    }
}
