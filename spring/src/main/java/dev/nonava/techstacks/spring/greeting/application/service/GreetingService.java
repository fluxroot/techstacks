/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.spring.greeting.application.service;

import dev.nonava.techstacks.spring.core.domain.model.Result;
import dev.nonava.techstacks.spring.greeting.domain.model.Greeting;
import dev.nonava.techstacks.spring.greeting.domain.model.Username;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    public Result<List<Greeting>, Exception> listGreetings() {
        var greeting = new Greeting(new Username("World"));
        return Result.of(List.of(greeting));
    }
}
