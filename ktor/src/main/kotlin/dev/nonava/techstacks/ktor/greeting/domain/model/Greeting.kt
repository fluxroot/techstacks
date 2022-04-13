/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.greeting.domain.model

import dev.nonava.techstacks.ktor.core.domain.model.Entity
import dev.nonava.techstacks.ktor.core.domain.model.Identity

class Greeting(
    val username: Username
) : Entity<Greeting> {
    override val id: Identity<Greeting> = Identity(type = Greeting::class)

    fun asString(): String = "Hello ${username.value}!"
}
