package com.arova.data.local

import com.arova.data.LocalUserProfileDao
import com.arova.domain.UserProfile

class RoomLocalUserProfileDao(private val dao: UserProfileDao) : LocalUserProfileDao {
    override suspend fun insertProfile(profile: UserProfile) {
        dao.insert(
            UserProfileEntity(
                id = 0,
                name = profile.name,
                age = profile.age,
                weight = profile.weight,
                height = profile.height,
                calorieGoal = profile.calorieGoal
            )
        )
    }

    override suspend fun getProfile(): UserProfile? {
        return dao.get()?.let {
            UserProfile(
                name = it.name,
                age = it.age,
                weight = it.weight,
                height = it.height,
                calorieGoal = it.calorieGoal
            )
        }
    }
}
