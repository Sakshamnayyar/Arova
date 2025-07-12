package com.arova.data.di

import com.arova.data.*
import com.arova.domain.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        fun provideLocalMealDao(): LocalMealDao = object : LocalMealDao {
            override fun insertMeal(meal: MealEntry) {}
        }
    }
}
