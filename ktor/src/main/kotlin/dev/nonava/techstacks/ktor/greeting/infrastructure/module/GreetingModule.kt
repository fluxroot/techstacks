/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.greeting.infrastructure.module

import dev.nonava.techstacks.ktor.greeting.application.service.GreetingService
import org.koin.core.module.Module
import org.koin.dsl.module

val greetingModule: Module = module {
    single { GreetingService() }
}
