package com.arova.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FoodInfoResponse(
    @SerialName("calories") val calories: Int,
    @SerialName("protein") val protein: Double,
    @SerialName("carbs") val carbs: Double,
    @SerialName("fat") val fat: Double
)
