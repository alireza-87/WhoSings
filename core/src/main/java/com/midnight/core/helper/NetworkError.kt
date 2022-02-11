package com.midnight.core.helper

sealed class NetworkErrorType(override val message: String? = null) : Throwable(message){
    data class NetworkConnection(override val message: String? = null) : NetworkErrorType(message)
    data class BadRequest(override val message: String? = null) : NetworkErrorType(message)
    data class UnAuthorized(override val message: String?= null) : NetworkErrorType(message)
    data class InternalServerError(override val message: String?= null) : NetworkErrorType(message)
    data class ResourceNotFound(override val message: String?= null) : NetworkErrorType(message)
    data class Unknown(override val message: String? = null) : NetworkErrorType()
}