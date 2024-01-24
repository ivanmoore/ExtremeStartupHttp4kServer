package com.oocode

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler = routes(
    "/ping" bind GET to {
        Response(OK).body("pong")
    }
)

fun main() {
    val server = ExtremeStartupHttp4kServer.http4kServer.start()
    println("Server started on " + server.port())
}

object ExtremeStartupHttp4kServer {
    val http4kServer = PrintRequest().then(app).asServer(SunHttp(9000))
}