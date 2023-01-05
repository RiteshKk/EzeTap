package com.riteshkumar.ezetap.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
){
    class Loading<T>(): Resource<T>()
    class Success<T>(data: T): Resource<T>(data)
    class Failure<T>(data: T? = null, message: String): Resource<T>(data,message)
}
