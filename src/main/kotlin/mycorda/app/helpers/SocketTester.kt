package mycorda.app.helpers

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.net.SocketTimeoutException

/**
 * Check as socket is available. The basic low level
 * health test
 */
object SocketTester {

    fun isLive(host: String, port: Int, timeoutMs: Int = 1000): Boolean {
        var isAlive = false
        val socketAddress = InetSocketAddress(host, port)
        val socket = Socket()
        try {
            socket.connect(socketAddress, timeoutMs)
            isAlive = true
        } catch (steIgnored: SocketTimeoutException) {
            println(steIgnored)
        } catch (ioeIgnored: IOException) {
            println(ioeIgnored)
        }

        return isAlive
    }
}

