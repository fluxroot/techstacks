/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.reactive.greeting.application.service;

import dev.nonava.techstacks.quarkus.reactive.greeting.domain.model.Greeting;
import dev.nonava.techstacks.quarkus.reactive.greeting.domain.model.Username;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingService {
    public Multi<Greeting> listGreetings() {
        var greeting = new Greeting(new Username("World"));
        return Multi.createFrom().item(greeting);
    }
}
