package mycorda.app.clock

/**
 * Some basic timer primitives that behave appropriately
 * depending on the OS / hardware
 */
object PlatformTimer {

    /**
     * Should match the granularity of the underlying
     * clock "tick" on the platform. The idea is to be able to tune
     * sleeps in tests efficiently. For example on the original PC the
     * clock ticker 18.2 times a second, so the min time to wait is approx 1000/18. Thank
     * fully modern computers it normally less than this is
     */
    fun clockTick(): Long {
        val os = System.getProperty("os.name")

        // tune as required - it might needs more detailed analysis of the
        // underlying hardware
        return when (os) {
            "Mac OS X" -> 1 //  :-)
            "Linux" -> 10   //  currently tuned for CircleCI
            else -> 20
        }
    }

    fun sleepForTicks(ticks: Int) = Thread.sleep(clockTick() * ticks)

}