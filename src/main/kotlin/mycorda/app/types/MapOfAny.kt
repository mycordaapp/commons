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



