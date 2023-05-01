package com.example.roomdemo.database.dao

import androidx.room.*
import com.example.roomdemo.database.models.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task ORDER BY id DESC")
    fun getTaskList(): Flow<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: TaskItem)

    @Query("DELETE FROM Task")
    suspend fun deleteAllTasks()

    @Delete
    suspend fun deleteTask(task: TaskItem)
}