/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {Username} from "./username";
import {Entity} from "../../../core/domain/model/entity";
import {Identity} from "../../../core/domain/model/identity";

export class Greeting implements Entity<Greeting> {
    readonly id: Identity<Greeting> = new Identity<Greeting>();

    constructor(readonly username: Username) {
    }

    asString(): string {
        return `Hello ${this.username.value}`;
    }
}
