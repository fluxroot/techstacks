/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {Injectable} from "@nestjs/common";
import {Greeting} from "../../domain/model/greeting";
import {Result} from "../../../core/domain/model/result";
import {Username} from "../../domain/model/username";

@Injectable()
export class GreetingService {
    listGreetings(): Result<Greeting[], Error> {
        let greeting = new Greeting(new Username('World'));
        return Result.of([greeting]);
    }
}
