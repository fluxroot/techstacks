/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

export class Result<S, F> {
    private constructor(private readonly success: S, private readonly failure: F) {
    }

    static of<S, F>(success: S): Result<S, F> {
        return new Result(success, null);
    }

    static failure<S, F>(failure: F): Result<S, F> {
        return new Result(null, failure);
    }

    isSuccess(): boolean {
        return this.success != null;
    }

    isFailure(): boolean {
        return this.failure != null;
    }

    onSuccess(action: (success: S) => void): void {
        if (this.isSuccess()) {
            action(this.success)
        }
    }

    onFailure(action: (failure: F) => void): void {
        if (this.isFailure()) {
            action(this.failure);
        }
    }

    orElseThrow<E extends Error>(onFailure: (failure: F) => E): S {
        if (this.isFailure()) {
            throw onFailure(this.failure);
        }
        return this.success;
    }

    map<U>(onSuccess: (success: S) => U): Result<U, F> {
        if (this.isSuccess()) {
            return Result.of(onSuccess(this.success))
        } else {
            return Result.failure(this.failure);
        }
    }

    flatMap<U>(onSuccess: (success: S) => Result<U, F>): Result<U, F> {
        if (this.success) {
            return onSuccess(this.success);
        } else {
            return Result.failure(this.failure);
        }
    }

    transform<U>(onSuccess: (success: S) => U, onFailure: (failure: F) => U): U {
        if (this.isSuccess()) {
            return onSuccess(this.success);
        } else {
            return onFailure(this.failure);
        }
    }

    toString = (): string => {
        if (this.isSuccess()) {
            return `Success[${this.success}]`;
        } else {
            return `Failure[${this.failure}]`;
        }
    }
}
