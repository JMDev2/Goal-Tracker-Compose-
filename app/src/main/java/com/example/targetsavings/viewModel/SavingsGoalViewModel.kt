package com.example.targetsavings.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.targetsavings.data.entity.GoalContribution
import com.example.targetsavings.data.entity.SavingsGoal
import com.example.targetsavings.data.repository.SavingsGoalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavingsGoalViewModel @Inject constructor(
    private val repository: SavingsGoalRepository
) : ViewModel() {

    private val _transactionType = MutableStateFlow("Deposit")
    val transactionType: StateFlow<String> = _transactionType

    fun setTransactionType(type: String) {
        _transactionType.value = type
    }

    private val _contributions = MutableStateFlow<List<GoalContribution>>(emptyList())
    val contributions: StateFlow<List<GoalContribution>> = _contributions

    // Insert a new goal
    fun insertGoal(goal: SavingsGoal) {
        viewModelScope.launch {
            repository.insertGoal(goal)
        }
    }


    init {
        viewModelScope.launch {
            val count = repository.contributionDao.countContributions()
            Log.d("ROOM_CHECK", "Total contributions in DB = $count")
        }
    }
    init {
        viewModelScope.launch {
            val goalIds = repository.contributionDao.getAllGoalIds()
            Log.d("ROOM_CHECK", "Stored goalIds = $goalIds")
        }
    }



    // List of all goals as StateFlow
    val goals: StateFlow<List<SavingsGoal>> =
        repository.getAllGoals()
            .combine(repository.getAllContributions()) { goalsList, contributionsList ->
                goalsList.map { goal ->
                    val totalContributed = contributionsList
                        .filter { it.goalId == goal.id }
                        .sumOf { it.amount }
                    goal.copy(currentAmount = totalContributed)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = emptyList()
            )


    // Insert a new contribution and update the goal's current amount
    fun addContribution(contribution: GoalContribution) {
        viewModelScope.launch {
            repository.insertContribution(contribution)
            contribution.goalId?.let { addToGoalCurrentAmount(it, contribution.amount) }
        }
    }

    // Increment the currentAmount of a goal directly in the database
    fun addToGoalCurrentAmount(goalId: String, amount: Double) {
        viewModelScope.launch {
            repository.addToGoalCurrentAmount(goalId, amount)
        }
    }


    //contributons per goal
    fun loadContributions(goalId: String) {
        viewModelScope.launch {
            repository.getContributionsForGoal(goalId).collect { list ->
                _contributions.value = list
            }
        }
    }
    // Optional: Flow of total contributed for a specific goal
//    fun getTotalContributed(goalId: String): Flow<Double> = repository.getTotalContributed(goalId)
}


