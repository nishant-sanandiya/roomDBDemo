package com.example.roomdemo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdemo.database.models.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task ORDER BY id DESC")
    fun getTaskList(): Flow<List<TaskItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(word: TaskItem)

    @Query("DELETE FROM Task")
    suspend fun deleteTask()
}