package com.arova.arova.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arova.domain.GetUserProfileUseCase
import com.arova.domain.SaveUserProfileUseCase
import com.arova.domain.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val saveUserProfileUseCase: SaveUserProfileUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase
) : ViewModel() {

    private val _profile = MutableStateFlow<UserProfile?>(null)
    val profile: StateFlow<UserProfile?> = _profile

    fun loadProfile() {
        viewModelScope.launch {
            _profile.value = getUserProfileUseCase()
        }
    }

    fun onSaveProfile(
        name: String,
        age: Int,
        weight: Double,
        height: Double,
        calorieGoal: Int
    ) {
        viewModelScope.launch {
            val profile = UserProfile(name, age, weight, height, calorieGoal)
            saveUserProfileUseCase(profile)
            _profile.value = profile
        }
    }
}
