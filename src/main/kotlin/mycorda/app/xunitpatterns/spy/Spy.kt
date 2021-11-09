package mycorda.app.xunitpatterns.spy

/**
 * A very basic Spy - see http://xunitpatterns.com/Test%20Spy.html
 */
class Spy {
    private val secrets = ArrayList<String>()
    fun spy(secret: String): Spy {
        secrets.add(secret)
        return this
    }

    fun secrets(): List<String> = secrets

    override fun equals(other: Any?): Boolean {
        return if (other is Spy) secrets() == secrets() else false
    }

    override fun hashCode(): Int {
        return secrets.hashCode()
    }
}