/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor

import dev.nonava.techstacks.ktor.greeting.infrastructure.controller.greetingRouting
import dev.nonava.techstacks.ktor.greeting.infrastructure.module.greetingModule
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.ktor.ext.Koin

fun main() {
    embeddedServer(
        factory = Netty,
        port = 8080,
        watchPaths = emptyList()
    ) {
        installKoin()
        installContentNegotiation()
        installRoutes()
    }.start(wait = true)
}

private fun Application.installKoin() {
    install(Koin) {
        modules(greetingModule)
    }
}

private fun Application.installContentNegotiation() {
    install(ContentNegotiation) {
        json()
    }
}

private fun Application.installRoutes() {
    greetingRouting()
}
