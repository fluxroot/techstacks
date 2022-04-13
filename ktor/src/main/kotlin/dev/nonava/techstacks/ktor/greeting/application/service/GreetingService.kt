/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.greeting.application.service

import dev.nonava.techstacks.ktor.core.domain.model.Result
import dev.nonava.techstacks.ktor.greeting.domain.model.Greeting
import dev.nonava.techstacks.ktor.greeting.domain.model.Username

class GreetingService {
    fun listGreetings(): Result<List<Greeting>, Exception> {
        val greeting = Greeting(Username("World"))
        return Result.of(listOf(greeting))
    }
}
