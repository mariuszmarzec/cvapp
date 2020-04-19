package com.marzec.cv.domain.model

sealed class Resource<T> {

    data class Data<T>(val content: T) : Resource<T>()

    data class Loading<T>(val content: T? = null) : Resource<T>()

    data class Error<T>(val error: Throwable) : Resource<T>()
}