package mycorda.app.types

/**
 * For places where generics is demanding something to check against, but we don't actually
 * need anything.
 */
class NotRequired {
    companion object {
        fun instance(): NotRequired {
            return NotRequired()
        }
    }
}