package com.oocode

import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ExtremeStartupHttp4kServerTest {
    // This is how you'd typically test http4k services
    @Test
    fun `Ping test using top level HttpHandler`() {
        assertEquals(Response(OK).body("pong"), app(Request(GET, "/ping")))
    }

    // Below here tests this http4k service by starting it, which is often overkill
    // Included here to show the http4k equivalent of
    // ExtremeStartupHttpServerTest in https://github.com/ivanmoore/extreme_startup_player
    @Test
    fun `Ping test using real running service`() {
        val httpClient = JavaHttpClient()
        assertEquals("pong", httpClient(Request(GET, "http://localhost:9000/ping")).bodyString())
    }

    companion object {
        @JvmStatic
        @BeforeAll
        fun startServer() {
            ExtremeStartupHttp4kServer.http4kServer.start()
        }

        @JvmStatic
        @AfterAll
        fun stopServer() {
            ExtremeStartupHttp4kServer.http4kServer.stop()
        }
    }
}
