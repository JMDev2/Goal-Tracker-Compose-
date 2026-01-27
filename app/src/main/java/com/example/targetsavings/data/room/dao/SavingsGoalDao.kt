package com.example.targetsavings.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.targetsavings.data.entity.SavingsGoal
import kotlinx.coroutines.flow.Flow

@Dao
interface SavingsGoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: SavingsGoal)



    @Query("SELECT * FROM goal ORDER BY targetDate ASC")
    fun getAllGoals(): Flow<List<SavingsGoal>>

    @Query("UPDATE goal SET currentAmount = currentAmount + :amount WHERE id = :goalId")
    suspend fun addToCurrentAmount(goalId: String, amount: Double)

}
