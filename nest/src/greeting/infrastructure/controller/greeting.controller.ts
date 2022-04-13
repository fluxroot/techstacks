/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {GreetingService} from "../../application/service/greeting.service";
import {Controller, Get, HttpException, HttpStatus} from "@nestjs/common";
import {Greeting} from "../../domain/model/greeting";

@Controller('/greetings')
export class GreetingController {
    constructor(private readonly greetingService: GreetingService) {
    }

    @Get()
    listGreetings() {
        return this.greetingService
            .listGreetings()
            .map(greetings =>
                greetings.map(greeting => GreetingData.of(greeting)))
            .orElseThrow(failure => new HttpException(failure, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}

class GreetingData {
    constructor(readonly id: string, readonly value: string) {
    }

    static of(greeting: Greeting): GreetingData {
        return new GreetingData(greeting.id.asString(), greeting.asString())
    }
}
