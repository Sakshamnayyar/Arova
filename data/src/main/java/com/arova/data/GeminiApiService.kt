package com.arova.data

interface GeminiApiService {
    suspend fun parseNaturalLanguage(query: String): List<String>
}
