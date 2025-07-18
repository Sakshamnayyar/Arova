package com.arova.data

import com.arova.domain.UserRepository
import javax.inject.Inject

class InMemoryUserRepository @Inject constructor() : UserRepository {
    private data class Account(val firstName: String, val lastName: String, val email: String, val password: String)
    private val accounts = mutableListOf<Account>()

    override suspend fun signUp(firstName: String, lastName: String, email: String, password: String): Result<Unit> {
        return if (accounts.any { it.email == email }) {
            Result.failure(Exception("Email already registered"))
        } else {
            accounts.add(Account(firstName, lastName, email, password))
            Result.success(Unit)
        }
    }
}
