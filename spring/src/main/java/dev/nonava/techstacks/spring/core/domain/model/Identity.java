/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.core.domain.model;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = PRIVATE)
public final class Identity<T extends Entity<T>> {
    private final UUID id;
    private final Class<T> type;

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
