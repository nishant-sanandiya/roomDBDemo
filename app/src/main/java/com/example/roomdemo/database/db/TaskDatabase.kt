package com.example.roomdemo.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdemo.database.dao.TaskDao
import com.example.roomdemo.database.models.TaskItem

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [TaskItem::class], version = 1, exportSchema = true)
public abstract class TaskDatabase: RoomDatabase() {

    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "TaskDatabase"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}