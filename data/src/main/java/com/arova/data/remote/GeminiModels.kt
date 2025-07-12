package com.arova.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    @SerialName("query") val query: String
)

@Serializable
data class GeminiResponse(
    @SerialName("items") val items: List<String>
)
