package com.example.roomdemo.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.roomdemo.database.application.TasksApplication
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.databinding.ActivityAddTaskBinding
import com.example.roomdemo.utils.KeyboardUtils
import com.example.roomdemo.viewModal.TaskViewModal
import com.example.roomdemo.viewModal.TaskViewModelFactory
import kotlinx.coroutines.launch
import java.util.*

class AddTask : AppCompatActivity(), BaseActivity, View.OnClickListener {

    private lateinit var binding: ActivityAddTaskBinding
    private val random = Random()
    private var editableTask: TaskItem? = null

    private val taskViewModel: TaskViewModal by viewModels {
        TaskViewModelFactory((application as TasksApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uiBinding()
        attachListners()
        initActionbar()
        initData()
    }

    override fun uiBinding() {
    }

    override fun attachListners() {
        binding.root.setOnClickListener(this)
        binding.addTaskButton.setOnClickListener(this)
    }

    override fun initActionbar() {
    }

    @SuppressLint("SetTextI18n")
    override fun initData() {
        val passTask = intent.extras?.get("task")
        if (passTask != null) {
            val temp = passTask as TaskItem
            binding.addTaskButton.text = "UPDATE TASK"
            binding.editTextTextPersonName.setText(temp.title)
            editableTask = temp
            Log.d("passTask", "$temp")
            title = "Edit Task"
        } else {
            title = "Add Task"
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.addTaskButton -> {
                val titleText = binding.editTextTextPersonName.text.toString()
                if (titleText != "") {
                    addTaskHandler(titleText)
                }
            }
        }
        v?.let { KeyboardUtils.hideKeyboard(this, it) }
    }

    private fun getRandomNumber(from: Int, to: Int): Int {
        if (editableTask == null) {
            return random.nextInt(to - from) + from
        } else {
            return editableTask!!.id
        }
    }

    private fun getTask(title: String): TaskItem {
        if (editableTask != null) {
            return TaskItem(
                getRandomNumber(0, 150), title, editableTask?.time, Date().toString()
            )
        } else {
            return TaskItem(
                getRandomNumber(0, 150), title, Date().toString(), Date().toString()
            )
        }
    }

    private fun addTaskHandler(title: String) {
        lifecycleScope.launch {
            val task = getTask(title)
            taskViewModel.insert(task)
            this@AddTask.finish()
        }
    }

}