/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.servlet.greeting.domain.model;

import dev.nonava.techstacks.spring.servlet.core.domain.model.Entity;
import dev.nonava.techstacks.spring.servlet.core.domain.model.Identity;
import lombok.Getter;

@Getter
public final class Greeting implements Entity<Greeting> {
    private final Identity<Greeting> id = Identity.of(Greeting.class);
    private final Username username;

    public Greeting(Username username) {
        this.username = username;
    }

    public String asString() {
        return String.format("Hello %s!", username.getValue());
    }
}
