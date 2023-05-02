package com.example.roomdemo.database.dao

import androidx.room.*
import com.example.roomdemo.database.models.TaskDescription
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDescriptionDao {
    @Query("SELECT * FROM TaskDescription ORDER BY updatedAt DESC")
    fun getTaskDescriptionList(): Flow<List<TaskDescription>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTaskDescription(task: TaskDescription)

    @Query("DELETE FROM TaskDescription")
    suspend fun deleteAllTaskDescription()

    @Delete
    suspend fun deleteTaskDescription(task: TaskDescription)
}