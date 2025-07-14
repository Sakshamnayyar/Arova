package com.arova.domain

interface UserRepository {
    suspend fun signUp(name: String, email: String, password: String): Result<Unit>
}
