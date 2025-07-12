package com.arova.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MealEntryEntity::class, FoodItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}
