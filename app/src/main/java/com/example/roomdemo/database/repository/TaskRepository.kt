package com.example.roomdemo.database.repository

import androidx.annotation.WorkerThread
import com.example.roomdemo.database.dao.TaskDao
import com.example.roomdemo.database.models.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val getAllTasksList: Flow<List<TaskItem>> = taskDao.getTaskList()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addTaskInDb(task: TaskItem) {
        taskDao.addTask(task)
    }

}