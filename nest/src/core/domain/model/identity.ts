/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {v4 as uuidv4} from 'uuid'
import {Entity} from "./entity";

export class Identity<T extends Entity<T>> {
    constructor(readonly id: string = uuidv4()) {
    }

    asString(): string {
        return this.id
    }
}
