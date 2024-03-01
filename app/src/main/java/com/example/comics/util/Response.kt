package com.example.comics.util

sealed class Response<out T : Any> {
    class Success<out T : Any>(val value: T) : Response<T>()
    class Error(val exception: Throwable) : Response<Nothing>()
}