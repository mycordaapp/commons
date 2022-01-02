package mycorda.app.types

/**
 * Simplify the generic Map of anything (or Map of Maps) type
 */
typealias MapOfAny = Map<String, Any?>


fun Map<*, *>.toMapOfAny(): MapOfAny {
    if (this.keys.any { it == null }) throw RuntimeException("1 or more keys is null")
    if (this.keys.any { it!!::class != String::class }) throw RuntimeException("1 or more keys is not a String")
    @Suppress("UNCHECKED_CAST")
    return this as MapOfAny
}

fun MapOfAny.unpackInt(key: String): Int {
    val value = this[key]
    return when (value) {
        is Int -> {
            value
        }
        is Long -> {
            value.toInt()
        }
        is String -> {
            value.toInt()
        }
        else -> {
            throw java.lang.RuntimeException("value ${value} for key $key cannot be converted to an Int")
        }
    }
}

fun MapOfAny.unpackLong(key: String): Long {
    val value = this[key]
    return when (value) {
        is Int -> {
            value.toLong()
        }
        is Long -> {
            value
        }
        is String -> {
            value.toLong()
        }
        else -> {
            throw java.lang.RuntimeException("value ${value} for key $key cannot be converted to a Long")
        }
    }
}


interface ToMapOfAny {
    fun toMap(): MapOfAny
}

interface FromMapOfAny<T> {
    fun fromMap(data: MapOfAny): T
}



