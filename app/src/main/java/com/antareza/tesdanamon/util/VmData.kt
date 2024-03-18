package com.antareza.tesdanamon.util

sealed class VmData<out T> {
    object Default : VmData<Nothing>()
    object Loading : VmData<Nothing>()
    data class Success<out T>(val data: T) : VmData<T>()
    data class Failure(val throwable: Throwable) : VmData<Nothing>()
    object Empty : VmData<Nothing>()
}