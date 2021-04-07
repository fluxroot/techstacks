/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.core.infrastructure.controller

import dev.nonava.techstacks.ktor.core.domain.model.Result
import dev.nonava.techstacks.ktor.core.domain.model.transform
import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

suspend inline fun <reified S : Any, F> ApplicationCall.respondResult(result: Result<S, F>) {
    result.transform(
        onSuccess = { success -> respond(HttpStatusCode.OK, success) },
        onFailure = { respond(HttpStatusCode.InternalServerError) }
    )
}
