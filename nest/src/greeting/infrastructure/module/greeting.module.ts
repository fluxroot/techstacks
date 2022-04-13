/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {Module} from "@nestjs/common";
import {GreetingController} from "../controller/greeting.controller";
import {GreetingService} from "../../application/service/greeting.service";

@Module({
    imports: [],
    controllers: [GreetingController],
    providers: [GreetingService]
})
export class GreetingModule {
}
