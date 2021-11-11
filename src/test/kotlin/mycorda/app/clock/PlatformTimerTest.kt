package mycorda.app.clock

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isEmpty
import org.junit.jupiter.api.Test
import java.util.*

class PlatformTimerTest {

    @Test
    fun `should be tuned for the underlying platform`() {
        // test constants
        val totalThreads = 10
        val totalIterations = 100
        val reportingInterval = 10

        val threads = ArrayList<TimerTestThread>()
        (1..totalThreads).forEach { threadNumber ->
            val t = TimerTestThread(threadNumber, totalIterations, reportingInterval)
            t.run()
            threads.add(t)
        }
        // should be waiting for threads not dumbly sleeping
        Thread.sleep(PlatformTimer.clockTick() * (totalIterations * 1.2).toLong())
        assertThat(threads.filter { !it.success() }, isEmpty)
        assertThat(threads.filter { !it.completed() }, isEmpty)
    }
}

class TimerTestThread(
    private val threadNumber: Int,
    private val totalIterations: Int,
    private val reportingInterval: Int
) : Thread() {
    private var success = true
    private var completed = false

    override fun run() {
        // have some randomness on thread start
        sleep(Random().nextInt(100).toLong())

        (1..totalIterations).forEach { iterationNumber ->
            if (iterationNumber % reportingInterval == 0) {
                println("thread $threadNumber, $iterationNumber")
            }
            val startTime = System.currentTimeMillis()
            PlatformTimer.sleepForTicks(1)
            if (System.currentTimeMillis() == startTime) {
                println("thread $threadNumber, $iterationNumber failed")
                success = false
            }
        }

        completed = true
    }

    fun success(): Boolean = success
    fun completed(): Boolean = completed
}

