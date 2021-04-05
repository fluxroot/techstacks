/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.greeting.domain.model;

import dev.nonava.techstacks.spring.core.domain.model.Entity;
import dev.nonava.techstacks.spring.core.domain.model.Identity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public final class Greeting implements Entity<Greeting> {
    private final Identity<Greeting> id;
    private final Username username;

    public Greeting(Username username) {
        this.id = Identity.of(Greeting.class);
        this.username = username;
    }

    public String asString() {
        return String.format("Hello %s!", username.getValue());
    }
}
