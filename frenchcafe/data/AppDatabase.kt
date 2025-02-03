package com.example.frenchcafe.data // Your data package

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GroceryItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() { // Use AppDatabase here
    abstract fun groceryDao(): GroceryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null // Use AppDatabase here

        fun getDatabase(context: Context): AppDatabase { // Use AppDatabase here
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, // Use AppDatabase here
                    "app_database" // Or your preferred database name
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}