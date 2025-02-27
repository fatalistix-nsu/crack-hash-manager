package com.github.fatalistix.routes.external

import com.github.fatalistix.services.CrackService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.get

fun Application.registerCrackRoutes() {
    val service: CrackService = get()

    routing {
        route("/api/hash") {
            startCrack(service)
            getStatus(service)
        }
    }
}