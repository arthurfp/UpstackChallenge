package com.arthurfp.upchallenge.utils

import retrofit2.Response

fun <T> Response<T>.handleResponse(): NetworkResult<T> {
    return when {
        this.message().toString().contains("timeout") -> {
            NetworkResult.Error("Timeout.")
        }
        this.isSuccessful -> {
            NetworkResult.Success(this.body())
        }
        // TODO: handle more response status errors with the proper error message
        else -> {
            NetworkResult.Error("No data found!")
        }
    }
}