package com.example.zohotaskapp.utils

import retrofit2.Response

object Utils {
    fun <T : Any> handleApiError(resp: T): AppResult.Error {
//        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception("error"))
    }

    fun <T : Any> handleSuccess(response: T): AppResult<T> {
//        response.body()?.let {
//            return AppResult.Success(it)
//        } ?: return handleApiError(response)

        return AppResult.Success(response)
    }
}