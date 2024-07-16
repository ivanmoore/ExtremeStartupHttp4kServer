package com.oocode

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/" bind GET to {
        val queries = it.queries("q")
        Response(OK).body(queries.firstOrNull()?.let { question ->
            println("question = ${question}")
            Answerer().answerFor(question) } ?: HomePage.HTML)
    }
)

fun main() {
    val server = ExtremeStartupHttp4kServer.http4kServer.start()
    println("Server started on " + server.port())
}

object ExtremeStartupHttp4kServer {
    val http4kServer = app.asServer(SunHttp(8124))
}

private object HomePage {
    val HTML = """
<html>
    <body>
    <h1>Extreme startup player (http4k)</h1>
    <p>This is a minimal web app starting point for <a href='https://extreme-startup.fly.dev/'>Extreme startup</a></p>
    </body>
</html>""".trim()
}
