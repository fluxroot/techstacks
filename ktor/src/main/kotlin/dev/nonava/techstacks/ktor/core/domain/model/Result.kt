/*
 * Copyright 2021 Phokham Nonava
 *
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package dev.nonava.techstacks.ktor.core.domain.model

sealed class Result<out S, out F> {
    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    companion object {
        fun <S> of(success: S): Result<S, Nothing> = Success(success)
        fun <F> failure(failure: F): Result<Nothing, F> = Failure(failure)
    }
}

@PublishedApi
internal data class Success<S>(
    val success: S
) : Result<S, Nothing>()

@PublishedApi
internal data class Failure<F>(
    val failure: F
) : Result<Nothing, F>()

inline fun <S, F> Result<S, F>.onSuccess(action: (S) -> Unit) {
    if (this is Success) {
        action(success)
    }
}

inline fun <S, F> Result<S, F>.onFailure(action: (F) -> Unit) {
    if (this is Failure) {
        action(failure)
    }
}

inline fun <S, F, U> Result<S, F>.map(onSuccess: (S) -> U): Result<U, F> =
    when (this) {
        is Success -> Result.of(onSuccess(success))
        is Failure -> this
    }

inline fun <S, F, U> Result<S, F>.flatMap(onSuccess: (S) -> Result<U, F>): Result<U, F> =
    when (this) {
        is Success -> onSuccess(success)
        is Failure -> this
    }

inline fun <S, F, U> Result<S, F>.transform(onSuccess: (S) -> U, onFailure: (F) -> U): U =
    when (this) {
        is Success -> onSuccess(success)
        is Failure -> onFailure(failure)
    }
