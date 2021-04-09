/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {Module} from '@nestjs/common';
import {GreetingModule} from "./greeting/infrastructure/module/greeting.module";

@Module({
    imports: [GreetingModule]
})
export class AppModule {
}
