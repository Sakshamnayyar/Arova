package com.arova.data

import com.arova.domain.UserProfile

interface LocalUserProfileDao {
    suspend fun insertProfile(profile: UserProfile)
    suspend fun getProfile(): UserProfile?
}
