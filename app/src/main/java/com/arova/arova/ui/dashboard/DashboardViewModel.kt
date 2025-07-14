package com.arova.arova.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.domain.DailySummary
import com.arova.domain.GetDailySummaryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getDailySummaryUseCase: GetDailySummaryUseCase
) : ViewModel() {

    private val _currentDate = MutableStateFlow(LocalDate.now())
    val currentDate: StateFlow<LocalDate> = _currentDate

    private val _summary = MutableStateFlow(DailySummary(0, 0.0, 0.0, 0.0))
    val summary: StateFlow<DailySummary> = _summary

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch { _summary.value = getDailySummaryUseCase(_currentDate.value) }
    }

    fun onPreviousDayClicked() {
        _currentDate.value = _currentDate.value.minusDays(1)
        refresh()
    }

    fun onNextDayClicked() {
        _currentDate.value = _currentDate.value.plusDays(1)
        refresh()
    }
}
