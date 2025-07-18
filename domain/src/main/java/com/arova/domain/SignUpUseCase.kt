package com.arova.domain

import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(firstName: String, lastName: String, email: String, password: String): Result<Unit> {
        return repository.signUp(firstName, lastName, email, password)
    }
}
