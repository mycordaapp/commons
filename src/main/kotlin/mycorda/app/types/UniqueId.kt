package mycorda.app.types

import mycorda.app.helpers.random
import java.util.*


/**
 * A generic encapsulation of a unique identifier that
 * can be generated in a range of formats like UUIDs or hashing
 * functions.
 *
 * Max length is 64, which allows for easy encoding of 256 bit hashes
 * The character set is limited to:
 *    - a..z
 *    - A..Z
 *    - 0..9
 *    - hyphen (-), underscore (_) and colon(:)
 */
class UniqueId(private val id: String = UUID.randomUUID().toString()) {

    init {
        // set some basic rules length rules
        if (id.isEmpty()) throw RuntimeException("UniqueId cannot be empty")
        if (id.length > 64) throw RuntimeException("UniqueId must be 64 characters or less")
        if (!pattern.matches(id)) throw RuntimeException("Invalid characters. Only alphanumeric, hyphen (-), underscore (_) and colon(:) supported")
    }

    companion object {
        val pattern = Regex("^[a-zA-Z0-9_:\\-]+\$")

        /**
         * From a random UUID
         */
        fun randomUUID(): UniqueId {
            return UniqueId(UUID.randomUUID().toString())
        }

        /**
         * From a provided String. Min length is 6, max is 64
         */
        fun fromString(id: String): UniqueId {
            return UniqueId(id)
        }

        /**
         * From a provided UUID
         */
        fun fromUUID(id: UUID): UniqueId {
            return UniqueId(id.toString())
        }

        /**
         * Build a random string in a 'booking reference' style,
         * e.g. `BZ13FG`
         */
        @Deprecated("use alphanumeric ")
        fun random(length: Int = 6): UniqueId {
            return UniqueId(String.random(length))
        }

        /**
         * Build a random string in a 'booking reference' style,
         * e.g. `BZ13FG`
         */
        fun alphanumeric(length: Int = 6): UniqueId {
            return UniqueId(String.random(length))
        }
    }

    override fun equals(other: Any?): Boolean {
        return if (other is UniqueId) {
            this.id == other.id
        } else false
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = id

}