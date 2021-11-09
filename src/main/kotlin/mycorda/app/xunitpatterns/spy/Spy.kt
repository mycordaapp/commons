package mycorda.app.xunitpatterns.spy

/**
 * A very basic Spy - see http://xunitpatterns.com/Test%20Spy.html
 */
class Spy {
    private val secrets = ArrayList<String>()
    fun spy(secret: String) = secrets.add(secret)
    fun secrets(): List<String> = secrets
}