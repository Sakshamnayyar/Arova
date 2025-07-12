package com.arova.data

interface GeminiApiService {
    fun parseNaturalLanguage(query: String): List<String>
}
