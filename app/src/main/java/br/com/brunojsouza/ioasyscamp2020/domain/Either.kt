package br.com.brunojsouza.ioasyscamp2020.domain

sealed class Either<out S, out F> {
    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Failure<out F>(val a: F) : Either<Nothing, F>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out S>(val b: S) : Either<S, Nothing>()

    val isRight get() = this is Success<S>
    val isLeft get() = this is Failure<F>

    fun <L> left(a: L) = Failure(a)
    fun <R> right(b: R) = Success(b)

    fun either(fnR: (S) -> Any, fnL: (F) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }
}
//
//// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
//// Composes 2 functions
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}
//
fun <T, F, S> Either<S, F>.flatMap(fn: (S) -> Either<T, F>): Either<T, F> =
    when (this) {
        is Either.Failure -> Either.Failure(
            a
        )
        is Either.Success -> fn(b)
    }

fun <T, S, F> Either<S, F>.map(fn: (S) -> (T)): Either<T, F> = this.flatMap(fn.c(::right))

typealias Result<T> = Either<T, Throwable>

typealias Failure<T> = Either.Failure<T>

typealias Success<T> = Either.Success<T>