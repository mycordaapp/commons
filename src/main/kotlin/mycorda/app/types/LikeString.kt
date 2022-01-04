package mycorda.app.types

/*
 For wildcard searching. Uses SQL LIKE rules

 Standard (ANSI) SQL has two wildcard characters for use with the LIKE keyword:
    _ (underscore). Matches a single occurrence of any single character.
    % (percent sign). Matches zero or more occurrences of any single character.

 */

data class LikeString(val like: String, val escape: Char = '!') {

    private val regex = like.replace("%", ".{0,}")
        .replace("_", ".").toRegex()

    fun toRegex(): Regex = regex

    fun matches(value: String): Boolean = regex.matches(value)
}

fun String.matches(likeString: LikeString): Boolean = likeString.matches(this)