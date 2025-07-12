package com.arova.data

/**
 * Generic interface for parsing natural language meal descriptions using any
 * language model provider. Implementations can delegate to services such as
 * Gemini or OpenAI.
 */
interface LanguageModelApiService {
    suspend fun parseNaturalLanguage(query: String): List<String>
}
