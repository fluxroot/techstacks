/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

import {Identity} from "./identity";

export interface Entity<T extends Entity<T>> {
    readonly id: Identity<T>;
}
