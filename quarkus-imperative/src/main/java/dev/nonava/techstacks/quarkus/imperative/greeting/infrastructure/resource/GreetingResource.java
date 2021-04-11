/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.imperative.greeting.infrastructure.resource;

import dev.nonava.techstacks.quarkus.imperative.greeting.application.service.GreetingService;
import dev.nonava.techstacks.quarkus.imperative.greeting.domain.model.Greeting;
import lombok.Value;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.stream.Collectors.toList;

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
    public Response listGreetings() {
        return greetingService
                .listGreetings()
                .map(greetings ->
                        greetings
                                .stream()
                                .map(GreetingData::of)
                                .collect(toList()))
                .transform(
                        greetingDataList -> Response.ok(greetingDataList).build(),
                        e -> Response.serverError().build());
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
