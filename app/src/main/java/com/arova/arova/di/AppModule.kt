package com.arova.arova.di

import com.arova.data.*
import com.arova.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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

    @Provides
    @Singleton
    fun provideFoodRepository(
        geminiApi: GeminiApiService,
        foodDbApi: FoodDatabaseApiService
    ): FoodRepository = FoodRepositoryImpl(geminiApi, foodDbApi)

    @Provides
    @Singleton
    fun provideMealRepository(localMealDao: LocalMealDao): MealRepository =
        MealRepositoryImpl(localMealDao)

    @Provides
    @Singleton
    fun provideParseMealUseCase(repository: FoodRepository): ParseMealUseCase =
        ParseMealUseCase(repository)

    @Provides
    @Singleton
    fun provideSaveMealEntryUseCase(repository: MealRepository): SaveMealEntryUseCase =
        SaveMealEntryUseCase(repository)
}
