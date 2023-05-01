package com.example.roomdemo.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.adapters.TaskListAdapter
import com.example.roomdemo.database.application.TasksApplication
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.utils.KeyboardUtils
import com.example.roomdemo.viewModal.TaskViewModal
import com.example.roomdemo.viewModal.TaskViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), BaseActivity, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var listDataArray = mutableListOf<TaskItem>()
    private val taskViewModel: TaskViewModal by viewModels {
        TaskViewModelFactory((application as TasksApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uiBinding()
        attachListners()
        initActionbar()
        initData()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun uiBinding() {
        val adapter = TaskListAdapter(listDataArray)
        binding.taskListRecyclerView.adapter = adapter
        binding.taskListRecyclerView.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )
        val swipeHelperInstance = swipeHelper()
        swipeHelperInstance.attachToRecyclerView(binding.taskListRecyclerView);
        taskViewModel.allTasks.observe(this, Observer { tasks ->
            tasks?.let {
                listDataArray = (it as MutableList<TaskItem>)
                val adapter = TaskListAdapter(it)
                binding.taskListRecyclerView.adapter = adapter
                binding.taskListRecyclerView.adapter?.notifyDataSetChanged()
            }
        })
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
//                delay(2000)
//                taskViewModel.deleteAllTasks()
            } catch (e: java.lang.Exception) {
                Log.d("LOG", "$e")
            }
        }
    }

    private fun swipeHelper(): ItemTouchHelper {
        val helper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(
                    viewHolder: RecyclerView.ViewHolder,
                    direction: Int
                ) {
                    val position: Int = viewHolder.adapterPosition
                    val item = listDataArray[position]
                    taskViewModel.deleteTask(item)
                }
            })
        return helper
    }
}