package com.arova.data.di

import android.content.Context
import androidx.room.Room
import com.arova.data.*
import com.arova.data.local.AppDatabase
import com.arova.data.local.RoomLocalMealDao
import com.arova.data.local.RoomLocalUserProfileDao
import com.arova.data.remote.*
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
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

    @Binds
    fun bindUserProfileRepository(impl: UserProfileRepositoryImpl): UserProfileRepository

    @Binds
    fun bindUserRepository(impl: InMemoryUserRepository): UserRepository

    companion object {
        @Provides
        @Singleton
        fun provideJson(): Json = Json { ignoreUnknownKeys = true }

        @Provides
        @Singleton
        fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
            .build()

        @Provides
        @Singleton
        fun provideGeminiService(json: Json, client: OkHttpClient): GeminiService =
            Retrofit.Builder()
                .baseUrl("https://example.com/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .client(client)
                .build()
                .create()

        @Provides
        @Singleton
        fun provideFoodDatabaseService(json: Json, client: OkHttpClient): FoodDatabaseService =
            Retrofit.Builder()
                .baseUrl("https://example.com/")
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .client(client)
                .build()
                .create()

        @Provides
        @Singleton
        fun provideLanguageModelApiService(service: GeminiService): LanguageModelApiService =
            GeminiApiServiceImpl(service)

        @Provides
        @Singleton
        fun provideFoodDatabaseApiService(service: FoodDatabaseService): FoodDatabaseApiService =
            FoodDatabaseApiServiceImpl(service)

        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "arova.db").build()

        @Provides
        @Singleton
        fun provideLocalMealDao(db: AppDatabase): LocalMealDao = RoomLocalMealDao(db.mealDao())

        @Provides
        @Singleton
        fun provideLocalUserProfileDao(db: AppDatabase): LocalUserProfileDao =
            RoomLocalUserProfileDao(db.userProfileDao())
    }
}
