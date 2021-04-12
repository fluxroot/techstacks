/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.reactive.core.domain.model;

public interface Entity<T extends Entity<T>> {
    Identity<T> getId();
}
