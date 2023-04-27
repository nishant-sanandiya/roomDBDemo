package com.example.roomdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.data_classes.TaskItem
import com.example.roomdemo.R

class TaskListAdapter(private val data: List<TaskItem>) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitleTextView: TextView = view.findViewById(R.id.taskTitleText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_view, parent, false)
        view.setOnClickListener {
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTitleTextView.text = data[position].title
    }

    override fun getItemCount(): Int = data.size
}