/*
 * Copyright 2021-2022 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.imperative.core.domain.model;

import lombok.EqualsAndHashCode;

import java.util.function.Consumer;
import java.util.function.Function;

@EqualsAndHashCode
public final class Result<S, F> {
    private final S success;
    private final F failure;

    private Result(S success, F failure) {
        requireEitherSuccessOrFailure(success, failure);
        this.success = success;
        this.failure = failure;
    }

    public static <S, F> Result<S, F> of(S success) {
        return new Result<>(success, null);
    }

    public static <S, F> Result<S, F> failure(F failure) {
        return new Result<>(null, failure);
    }

    public boolean isSuccess() {
        return success != null;
    }

    public boolean isFailure() {
        return failure != null;
    }

    public void onSuccess(Consumer<? super S> action) {
        if (isSuccess()) {
            action.accept(success);
        }
    }

    public void onFailure(Consumer<? super F> action) {
        if (isFailure()) {
            action.accept(failure);
        }
    }

    public <E extends Throwable> S orElseThrow(Function<F, E> onFailure) throws E {
        if (isFailure()) {
            throw onFailure.apply(failure);
        }
        return success;
    }

    public <U> Result<U, F> map(Function<? super S, ? extends U> onSuccess) {
        if (isSuccess()) {
            return Result.of(onSuccess.apply(success));
        } else {
            return Result.failure(failure);
        }
    }

    public <U> Result<U, F> flatMap(Function<? super S, ? extends Result<U, F>> onSuccess) {
        if (isSuccess()) {
            return onSuccess.apply(success);
        } else {
            return Result.failure(failure);
        }
    }

    public <U> U transform(Function<? super S, U> onSuccess, Function<? super F, U> onFailure) {
        if (isSuccess()) {
            return onSuccess.apply(success);
        } else {
            return onFailure.apply(failure);
        }
    }

    @Override
    public String toString() {
        if (isSuccess()) {
            return String.format("Success[%s]", success);
        } else {
            return String.format("Failure[%s]", failure);
        }
    }

    private static <S, F> void requireEitherSuccessOrFailure(S success, F failure) {
        if ((success == null) == (failure == null)) {
            throw new IllegalArgumentException("Either success or failure must be set");
        }
    }
}
