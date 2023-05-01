package com.example.roomdemo.database.application

import android.app.Application
import com.example.roomdemo.database.db.TaskDatabase
import com.example.roomdemo.database.repository.TaskRepository

class TasksApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { TaskDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}