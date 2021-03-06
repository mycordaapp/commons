package mycorda.app.helpers

import java.util.*


fun String.Companion.random(length: Int = 6): String {
    val random = Random()
    val buffer = StringBuilder(length)
    for (i in 0 until length) {
        // will mix numeric and alpha in correct proportion
        if (random.nextFloat() < 10.0/36.0) {
            // 0..9
            val randomLimitedInt = 48 + (random.nextFloat() * (57 - 48 + 1)).toInt()
            buffer.append(randomLimitedInt.toChar())
        } else {
            // a..z
            val randomLimitedInt = 97 + (random.nextFloat() * (122 - 97 + 1)).toInt()
            buffer.append(randomLimitedInt.toChar())
        }

    }
    return buffer.toString()
}
