package com.oocode

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import com.natpryce.hamkrest.equalTo
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class ExtremeStartupHttp4kServerTest {
    // This is how you'd typically test http4k services
    @Test
    fun canAnswerMyName() {
        assertThat(app(Request(GET, "?q=What+is+your+name%3F")), equalTo(Response(OK).body("Someone")))
    }

    @Test
    fun showsHomePageIfMissingQueryString() {
        assertThat(app(Request(GET, "/")), hasBody(containsSubstring("<html>")))
    }

    // Below here tests this http4k service by starting it, which is often overkill
    // Included here to show the http4k equivalent of
    // ExtremeStartupHttpServerTest in https://github.com/ivanmoore/extreme_startup_player
    @Test
    fun `canAnswerMyName using real running service`() {
        val httpClient = JavaHttpClient()
        assertThat(httpClient(Request(GET, "http://localhost:8124?q=What+is+your+name%3F")), hasBody("Someone"))
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
