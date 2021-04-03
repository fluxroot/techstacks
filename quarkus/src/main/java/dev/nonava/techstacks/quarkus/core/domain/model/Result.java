/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.quarkus.core.domain.model;

import lombok.EqualsAndHashCode;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

@EqualsAndHashCode
public final class Result<R, F> {
    private final R result;
    private final F failure;

    private Result(R result, F failure) {
        requireEitherResultOrFailure(result, failure);
        this.result = result;
        this.failure = failure;
    }

    public static <R, F> Result<R, F> of(R result) {
        return new Result<>(result, null);
    }

    public static <R, F> Result<R, F> failure(F failure) {
        return new Result<>(null, failure);
    }

    public boolean isPresent() {
        return result != null;
    }

    public boolean isFailure() {
        return failure != null;
    }

    public boolean contains(R expectedResult) {
        return isPresent() && Objects.equals(result, expectedResult);
    }

    public boolean containsFailure(F expectedFailure) {
        return isFailure() && Objects.equals(failure, expectedFailure);
    }

    public void ifPresent(Consumer<? super R> action) {
        if (isPresent()) {
            action.accept(result);
        }
    }

    public void ifFailure(Consumer<? super F> action) {
        if (isFailure()) {
            action.accept(failure);
        }
    }

    public <E extends Throwable> R orElseThrow(Function<F, E> failureMapper) throws E {
        if (isFailure()) {
            throw failureMapper.apply(failure);
        }
        return result;
    }

    public R get() {
        if (isFailure()) {
            throw new NoSuchElementException("No result present");
        }
        return result;
    }

    public F getFailure() {
        if (isPresent()) {
            throw new NoSuchElementException("No failure present");
        }
        return failure;
    }

    public Optional<R> peek() {
        return Optional.ofNullable(result);
    }

    public Optional<F> peekFailure() {
        return Optional.ofNullable(failure);
    }

    public <S> Result<S, F> map(Function<? super R, ? extends S> mapper) {
        if (isPresent()) {
            return Result.of(mapper.apply(result));
        } else {
            return Result.failure(failure);
        }
    }

    public <S> Result<S, F> flatMap(Function<? super R, ? extends Result<S, F>> mapper) {
        if (isPresent()) {
            return mapper.apply(result);
        } else {
            return Result.failure(failure);
        }
    }

    public <S> S transform(Function<? super R, S> resultMapper, Function<? super F, S> failureMapper) {
        if (isPresent()) {
            return resultMapper.apply(result);
        } else {
            return failureMapper.apply(failure);
        }
    }

    @Override
    public String toString() {
        if (isPresent()) {
            return String.format("Result[%s]", result);
        } else {
            return String.format("Failure[%s]", failure);
        }
    }

    private static <R, F> void requireEitherResultOrFailure(R result, F failure) {
        if ((result == null) == (failure == null)) {
            throw new IllegalArgumentException("Either result or failure must be set");
        }
    }
}
