package io.r4v3npr0.favorites.core.util

class Result<Result, Failure: Throwable> private constructor() {
    var failure: Failure? = null
        private set

    var isFailure: Boolean = false
        private set

    var isSuccess: Boolean = false
        private set

    var result: Result? = null
        private set

    private constructor(failure: Failure): this() {
        this.failure = failure
        this.isFailure = true
    }

    private constructor(result: Result): this() {
        this.isSuccess = true
        this.result = result
    }

    companion object {
        @JvmStatic
        fun <R, F: Throwable> failure(failure: F): Result<R, F> = Result<R, F>(failure)

        @JvmStatic
        fun <R, F: Throwable> success(result: R): Result<R, F> = Result<R, F>(result)
    }
}