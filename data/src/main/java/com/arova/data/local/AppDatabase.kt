package com.arova.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [MealEntryEntity::class, FoodItemEntity::class, UserProfileEntity::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun userProfileDao(): UserProfileDao
}
