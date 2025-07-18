package com.arova.domain

interface UserRepository {
    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): Result<Unit>
}
