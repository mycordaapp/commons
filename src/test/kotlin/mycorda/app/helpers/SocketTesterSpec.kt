package mycorda.app.helpers


import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.greaterThanOrEqualTo
import com.natpryce.hamkrest.throws
import org.junit.Ignore
import org.junit.Test

class SocketTesterSpec {

    @Test
    fun `should fail for unavailable site`() {
        assert(!SocketTester.isLive("does.not.exist.com", 123))
    }

    @Test
    fun `should pass for available site`() {
        assert(SocketTester.isLive("bbc.co.uk", 80))
    }

    @Test
    fun `throws exception if invalid port `() {
        assertThat (  {SocketTester.isLive("bbc.co.uk", -1)}, throws<Exception>() )
    }

}