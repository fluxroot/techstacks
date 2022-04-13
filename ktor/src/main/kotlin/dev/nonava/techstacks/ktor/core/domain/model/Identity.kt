/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.core.domain.model

import java.util.UUID
import kotlin.reflect.KClass

data class Identity<T : Entity<T>>(
    val id: UUID = UUID.randomUUID(),
    val type: KClass<T>
) {
    fun asString(): String = id.toString()
}
