package com.example.roomdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.roomdemo.database.db.TaskDatabase
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.databinding.ActivityAddTaskBinding
import com.example.roomdemo.utils.KeyboardUtils
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity(), BaseActivity, View.OnClickListener {

    private lateinit var binding: ActivityAddTaskBinding

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

    override fun initData() {

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

    private fun addTaskHandler(title: String) {
        lifecycleScope.launch {
            TaskDatabase.getDatabase(this@AddTask).taskDao().addTask(TaskItem(20, title))
            this@AddTask.finish()
        }
    }

}