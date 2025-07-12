package com.arova.data.remote

import com.arova.data.LanguageModelApiService
import javax.inject.Inject

class GeminiApiServiceImpl @Inject constructor(
    private val service: GeminiService
) : LanguageModelApiService {
    override suspend fun parseNaturalLanguage(query: String): List<String> {
        return service.parse(GeminiRequest(query)).items
    }
}
