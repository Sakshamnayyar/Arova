package com.arova.data.remote

import com.arova.data.GeminiApiService
import javax.inject.Inject

class GeminiApiServiceImpl @Inject constructor(
    private val service: GeminiService
) : GeminiApiService {
    override suspend fun parseNaturalLanguage(query: String): List<String> {
        return service.parse(GeminiRequest(query)).items
    }
}
