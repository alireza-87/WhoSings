package com.midnight.core.helper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ApiCallHelper {
    companion object {
        private const val BAD_REQUEST = 400
        private const val INTERNAL_SERVER = 500
        private const val UNAUTHORIZED = 401
        private const val NOT_FOUND = 404

        suspend fun <T> safeApiCall(
            apiCall: suspend () -> T
        ): Flow<DataState<T>> = flow {
            emit(
                try {
                    DataState.Success(apiCall.invoke())
                } catch (throwable: Throwable) {
                    when (throwable) {
                        is IOException -> DataState.NetworkError(NetworkErrorType.NetworkConnection())
                        is HttpException -> DataState.NetworkError(extractHttpExceptions(throwable))
                        else -> DataState.NetworkError(NetworkErrorType.Unknown())
                    }
                }
            )
        }

        private fun extractHttpExceptions(ex: HttpException): NetworkErrorType {
            return try {
                when (ex.code()) {
                    BAD_REQUEST ->
                        NetworkErrorType.BadRequest(ex.message())

                    INTERNAL_SERVER ->
                        NetworkErrorType.InternalServerError(ex.message())

                    UNAUTHORIZED ->
                        NetworkErrorType.UnAuthorized(ex.message())

                    NOT_FOUND ->
                        NetworkErrorType.ResourceNotFound(ex.message())

                    else ->
                        NetworkErrorType.Unknown()

                }
            } catch (exception: Exception) {
                NetworkErrorType.Unknown()
            }
        }
    }
}