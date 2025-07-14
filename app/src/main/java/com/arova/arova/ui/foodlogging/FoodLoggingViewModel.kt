package com.arova.arova.ui.foodlogging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class FoodLoggingViewModel @Inject constructor(
    private val parseMealUseCase: ParseMealUseCase,
    private val saveMealEntryUseCase: SaveMealEntryUseCase
) : ViewModel() {

    val userInput = MutableStateFlow("")
    private val _parsedFoodItems = MutableStateFlow<List<FoodItem>>(emptyList())
    val parsedFoodItems: StateFlow<List<FoodItem>> = _parsedFoodItems

    val isLoading = MutableStateFlow(false)
    val errorMessage = MutableStateFlow<String?>(null)

    private val _mealSaved = MutableSharedFlow<Unit>()
    val mealSaved: SharedFlow<Unit> = _mealSaved

    fun onAnalyzeClicked() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                _parsedFoodItems.value = parseMealUseCase(userInput.value)
                errorMessage.value = null
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }

    fun onConfirmAndSaveClicked(mealType: String) {
        viewModelScope.launch {
            try {
                val meal = MealEntry(_parsedFoodItems.value, java.time.LocalDateTime.now(), mealType)
                saveMealEntryUseCase(meal)
                _parsedFoodItems.value = emptyList()
                userInput.value = ""
                _mealSaved.emit(Unit)
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}
