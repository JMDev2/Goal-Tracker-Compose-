package com.example.targetsavings.di

import android.content.Context
import com.example.targetsavings.data.db.AppDatabase
import com.example.targetsavings.data.db.DatabaseProvider
import com.example.targetsavings.data.repository.SavingsGoalRepository
import com.example.targetsavings.data.room.dao.SavingsGoalDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSavingsGoalDao(db: AppDatabase): SavingsGoalDao {
        return db.savingsGoalDao()
    }

    @Provides
    fun provideSavingsGoalRepository(dao: SavingsGoalDao): SavingsGoalRepository {
        return SavingsGoalRepository(dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return DatabaseProvider.getDatabase(context)
    }
}
