package com.example.roomdemo.viewModal

import androidx.lifecycle.*
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.database.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModal (private val repository: TaskRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allTasks: LiveData<List<TaskItem>> = repository.getAllTasksList.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(task:TaskItem) = viewModelScope.launch {
        repository.addTaskInDb(task)
    }

    fun deleteAllTasks() = viewModelScope.launch {
        repository.deleteAllTaskInDb()
    }

    fun deleteTask(task:TaskItem) = viewModelScope.launch {
        repository.deleteTaskInDb(task)
    }
}

class TaskViewModelFactory(private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModal::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModal(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}