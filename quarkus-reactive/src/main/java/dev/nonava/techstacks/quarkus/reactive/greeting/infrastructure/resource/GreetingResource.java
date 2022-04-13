/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.reactive.greeting.infrastructure.resource;

import dev.nonava.techstacks.quarkus.reactive.greeting.application.service.GreetingService;
import dev.nonava.techstacks.quarkus.reactive.greeting.domain.model.Greeting;
import io.smallrye.mutiny.Multi;
import lombok.Value;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/greetings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {
    private final GreetingService greetingService;

    @Inject
    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    public Multi<GreetingData> listGreetings() {
        return greetingService
                .listGreetings()
                .onItem().transform(GreetingData::of)
                .onFailure().transform(throwable -> new InternalServerErrorException());
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
