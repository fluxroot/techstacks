/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.greeting.infrastructure.controller

import dev.nonava.techstacks.ktor.core.domain.model.map
import dev.nonava.techstacks.ktor.core.infrastructure.controller.respondResult
import dev.nonava.techstacks.ktor.greeting.application.service.GreetingService
import dev.nonava.techstacks.ktor.greeting.domain.model.Greeting
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Application.greetingRouting() {
    val greetingService: GreetingService by inject()

    routing {
        get("/greetings") {
            val result = greetingService
                .listGreetings()
                .map { greetings -> greetings.map { it.toGreetingData() } }
            call.respondResult(result)
        }
    }
}

@Serializable
private data class GreetingData(
    val id: String,
    val value: String
)

private fun Greeting.toGreetingData(): GreetingData = GreetingData(id.asString(), asString())
