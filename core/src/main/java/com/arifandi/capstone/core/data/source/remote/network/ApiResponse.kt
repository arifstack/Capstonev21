package com.arifandi.capstone.core.data.source.remote.network

sealed class ApiResponse<out R> {
    data class Success<out T>(val articles: T) : ApiResponse<T>()
    data class Error(val status: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}