package com.priyan.nameprobability.util

sealed class DataResults<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : DataResults<T>(data, null)
    class Error<T>(message: String) : DataResults<T>(null, message)
}
