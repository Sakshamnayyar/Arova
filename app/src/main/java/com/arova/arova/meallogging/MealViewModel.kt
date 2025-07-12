package com.arova.arova.meallogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@HiltViewModel
class MealViewModel @Inject constructor(
    private val parseMealUseCase: ParseMealUseCase,
    private val saveMealEntryUseCase: SaveMealEntryUseCase
) : ViewModel() {

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
