package com.example.roomdemo.database.repository

import androidx.annotation.WorkerThread
import com.example.roomdemo.database.dao.TaskDao
import com.example.roomdemo.database.dao.TaskDescriptionDao
import com.example.roomdemo.database.models.TaskDescription
import com.example.roomdemo.database.models.TaskItem
import kotlinx.coroutines.flow.Flow

class TaskDescriptionRepository(private val taskDescriptionDao: TaskDescriptionDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val getAllTasksDescriptionListById: Flow<List<TaskDescription>> = taskDescriptionDao.getTaskDescriptionList()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addTaskDescriptionInDb(taskDescription: TaskDescription) {
        taskDescriptionDao.addTaskDescription(taskDescription)
    }

    suspend fun deleteAllTaskDescriptionInDb(){
        taskDescriptionDao.deleteAllTaskDescription()
    }

    suspend fun deleteTaskDescriptionInDb(taskDescription:TaskDescription){
        taskDescriptionDao.deleteTaskDescription(taskDescription)
    }
}