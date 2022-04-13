/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.core.domain.model

interface Entity<T : Entity<T>> {
    val id: Identity<T>
}
