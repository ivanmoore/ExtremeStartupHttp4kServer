package com.oocode

import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtremeStartupHttp4kServerTest {
    @Test
    fun `Ping test`() {
        assertEquals(Response(OK).body("pong"), app(Request(GET, "/ping")))
    }
}
