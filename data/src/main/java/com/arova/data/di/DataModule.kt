package com.arova.data.di

import android.content.Context
import androidx.room.Room
import com.arova.data.*
import com.arova.data.local.AppDatabase
import com.arova.data.local.RoomLocalMealDao
import com.arova.domain.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindFoodRepository(impl: FoodRepositoryImpl): FoodRepository

    @Binds
    fun bindMealRepository(impl: MealRepositoryImpl): MealRepository

    companion object {
        @Provides
        @Singleton
        fun provideGeminiApiService(): GeminiApiService = object : GeminiApiService {
            override fun parseNaturalLanguage(query: String): List<String> =
                query.split(",").map { it.trim() }
        }

        @Provides
        @Singleton
        fun provideFoodDatabaseApiService(): FoodDatabaseApiService =
            object : FoodDatabaseApiService {
                override fun getNutritionInfo(
                    name: String,
                    quantity: Double,
                    unit: String
                ): FoodItem = FoodItem(name, quantity, unit, 0, 0.0, 0.0, 0.0)
            }

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "aro va.db").build()

        @Provides
        @Singleton
        fun provideLocalMealDao(db: AppDatabase): LocalMealDao = RoomLocalMealDao(db.mealDao())
    }
}
