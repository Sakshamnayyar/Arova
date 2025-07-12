package com.arova.arova.meallogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.data.*
import com.arova.domain.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MealViewModel : ViewModel() {
    private val foodRepository = FoodRepositoryImpl(
        object : GeminiApiService {
            override fun parseNaturalLanguage(query: String): List<String> {
                return query.split(",").map { it.trim() }
            }
        },
        object : FoodDatabaseApiService {
            override fun getNutritionInfo(name: String, quantity: Double, unit: String): FoodItem {
                return FoodItem(name, quantity, unit, 0, 0.0, 0.0, 0.0)
            }
        }
    )
    private val mealRepository = MealRepositoryImpl(object : LocalMealDao {
        override fun insertMeal(meal: MealEntry) {}
    })

    private val parseMealUseCase = ParseMealUseCase(foodRepository)
    private val saveMealEntryUseCase = SaveMealEntryUseCase(mealRepository)

    private val _items = MutableStateFlow<List<FoodItem>>(emptyList())
    val items: StateFlow<List<FoodItem>> = _items

    fun onLogMealClicked(query: String) {
        viewModelScope.launch {
            _items.value = parseMealUseCase(query)
        }
    }

    fun onConfirmMeal(mealType: String) {
        viewModelScope.launch {
            val meal = MealEntry(_items.value, LocalDateTime.now(), mealType)
            saveMealEntryUseCase(meal)
        }
    }
}
