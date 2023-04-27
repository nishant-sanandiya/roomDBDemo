package com.example.roomdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.R
import com.example.roomdemo.adapters.TaskListAdapter
import com.example.roomdemo.data_classes.TaskItem
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.utils.KeyboardUtils

class MainActivity : AppCompatActivity(), BaseActivity, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var listDataArray = mutableListOf<TaskItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uiBinding()
        attachListners()
        initActionbar()
        initData()
    }

    override fun uiBinding() {
        val adapter = TaskListAdapter(listDataArray)
        binding.taskListRecyclerView.adapter = adapter
        binding.taskListRecyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
    }

    override fun attachListners() {
        binding.addTaskBtn.setOnClickListener(this)
    }

    override fun initActionbar() {

    }

    override fun initData() {

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.addTaskBtn -> {
                startActivity(Intent(this, AddTask::class.java))
            }
        }
        v?.let { KeyboardUtils.hideKeyboard(this, it) }
    }
}