package com.arova.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val age: Int,
    val weight: Double,
    val height: Double,
    val calorieGoal: Int
)
