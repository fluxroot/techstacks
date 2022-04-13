/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.imperative.greeting.application.service;

import dev.nonava.techstacks.quarkus.imperative.greeting.domain.model.Greeting;
import dev.nonava.techstacks.quarkus.imperative.greeting.domain.model.Username;
import io.github.fluxroot.result.Result;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GreetingService {
    public Result<List<Greeting>, Exception> listGreetings() {
        var greeting = new Greeting(new Username("World"));
        return Result.of(List.of(greeting));
    }
}
