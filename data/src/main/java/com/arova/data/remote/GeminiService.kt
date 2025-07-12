package com.arova.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface GeminiService {
    @POST("parse")
    suspend fun parse(@Body request: GeminiRequest): GeminiResponse
}
