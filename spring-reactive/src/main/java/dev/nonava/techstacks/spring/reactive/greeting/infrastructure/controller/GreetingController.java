/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.reactive.greeting.infrastructure.controller;

import dev.nonava.techstacks.spring.reactive.greeting.application.service.GreetingService;
import dev.nonava.techstacks.spring.reactive.greeting.domain.model.Greeting;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

@RestController()
@RequestMapping("/greetings")
public class GreetingController {
    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Flux<GreetingData> listGreetings() {
        return greetingService
                .listGreetings()
                .map(GreetingData::of)
                .onErrorResume(e -> Flux.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Value
    private static class GreetingData {
        String id;
        String value;

        private static GreetingData of(Greeting greeting) {
            return new GreetingData(
                    greeting.getId().asString(),
                    greeting.asString());
        }
    }
}
