/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.reactive.greeting.application.service;

import dev.nonava.techstacks.spring.reactive.greeting.domain.model.Greeting;
import dev.nonava.techstacks.spring.reactive.greeting.domain.model.Username;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GreetingService {
    public Flux<Greeting> listGreetings() {
        var greeting = new Greeting(new Username("World"));
        return Flux.just(greeting);
    }
}
