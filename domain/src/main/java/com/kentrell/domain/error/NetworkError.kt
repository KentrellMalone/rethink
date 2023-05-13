package com.kentrell.domain.error

sealed class NetworkError : Exception() {
    object Timeout : NetworkError()
    object NoInternet : NetworkError()
    object Server : NetworkError()
}
