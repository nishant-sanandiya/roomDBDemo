package com.example.roomdemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.adapters.TaskListAdapter
import com.example.roomdemo.database.db.TaskDatabase
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.utils.KeyboardUtils
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

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

    override fun onResume() {
        super.onResume()
        getTaskListHandler()
    }

    private fun getTaskListHandler() {
        lifecycleScope.launch {
            try {
                val list = TaskDatabase.getDatabase(this@MainActivity).taskDao().getTaskList()
                val list2 = list.toList().size
                val list3 = list.toList().size
                Log.d("TAG","$list2")
            } catch (e: java.lang.Exception) {
                Log.d("Error", "${e.toString()}")
            }
        }
    }
}