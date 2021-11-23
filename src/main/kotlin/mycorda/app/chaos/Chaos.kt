package mycorda.app.chaos

import mycorda.app.clock.PlatformTick
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

/**
 * Walk through a pattern - an F mean fail and '.' is a pass
 */
class FailWithPattern(
    private val pattern: String = ".F".repeat(100),
    private val exception: Exception = RuntimeException("unlucky, punk")
) : Chaotic {

    init {
        if (pattern.replace("F", "").replace(".", "").isNotEmpty()) {
            throw RuntimeException("invalid pattern '$pattern'")
        }
    }

    private var index = 0;
    override fun `go ahead make my day`() {
        if (index < pattern.length) {
            if (pattern[index++] == 'F') throw exception
        } else {
            println("FailWithPattern has exhausted the pattern")
        }
    }
}


class AlwaysFail(private val exception: Exception = RuntimeException("unlucky, punk")) : Chaotic {
    override fun `go ahead make my day`() {
        throw exception
    }
}

class DelayUptoNTicks(
    private val maxDelay: PlatformTick = PlatformTick.of(5),
    private val minDelay: PlatformTick = PlatformTick.of(0),
    private val random: Random = Random
) : Chaotic {
    override fun `go ahead make my day`() {
        val overallDelayMs = maxDelay.milliseconds() - minDelay.milliseconds()
        val randomDelay = random.nextLong(overallDelayMs)
        Thread.sleep(minDelay.milliseconds() + randomDelay)
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
