package com.example.targetsavings.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.repository.SavingsGoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavingsGoalViewModel @Inject constructor(
    private val repository: SavingsGoalRepository
) : ViewModel() {

    fun insertGoal(goal: SavingsGoal) {
        viewModelScope.launch {
            repository.insertGoal(goal)
        }
    }

    val goals: StateFlow<List<SavingsGoal>> = repository.getAllGoals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )
}
