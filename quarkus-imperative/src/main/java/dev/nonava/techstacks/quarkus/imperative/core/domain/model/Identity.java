/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.imperative.core.domain.model;

import lombok.Value;

import java.util.UUID;

@Value
public class Identity<T extends Entity<T>> {
    UUID id;
    Class<T> type;

    public static <T extends Entity<T>> Identity<T> of(Class<T> type) {
        return new Identity<>(UUID.randomUUID(), type);
    }

    public static <T extends Entity<T>> Identity<T> of(UUID id, Class<T> type) {
        return new Identity<>(id, type);
    }

    public String asString() {
        return id.toString();
    }
}
