package com.arova.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodDatabaseService {
    @GET("nutrition")
    suspend fun getInfo(
        @Query("name") name: String,
        @Query("qty") quantity: Double,
        @Query("unit") unit: String
    ): FoodInfoResponse
}
