package com.example.targetsavings.data.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "target_savings_db"
            )
                .fallbackToDestructiveMigration() // ✅ drops old DB and recreates
                .build()
            INSTANCE = instance
            instance
        }
    }
}
