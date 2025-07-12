package com.arova.data

import com.arova.domain.UserProfile
import com.arova.domain.UserProfileRepository
import javax.inject.Inject

class UserProfileRepositoryImpl @Inject constructor(
    private val localDao: LocalUserProfileDao
) : UserProfileRepository {
    override suspend fun saveProfile(profile: UserProfile) {
        localDao.insertProfile(profile)
    }

    override suspend fun getProfile(): UserProfile? {
        return localDao.getProfile()
    }
}
