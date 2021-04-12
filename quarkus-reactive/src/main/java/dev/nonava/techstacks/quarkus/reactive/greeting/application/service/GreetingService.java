/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.reactive.greeting.application.service;

import dev.nonava.techstacks.quarkus.reactive.greeting.domain.model.Greeting;
import dev.nonava.techstacks.quarkus.reactive.greeting.domain.model.Username;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GreetingService {
    public Uni<List<Greeting>> listGreetings() {
        var greeting = new Greeting(new Username("World"));
        return Uni.createFrom().item(List.of(greeting));
    }
}
