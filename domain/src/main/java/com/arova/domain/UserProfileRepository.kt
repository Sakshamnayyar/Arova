package com.arova.domain

interface UserProfileRepository {
    suspend fun saveProfile(profile: UserProfile)
    suspend fun getProfile(): UserProfile?
}
