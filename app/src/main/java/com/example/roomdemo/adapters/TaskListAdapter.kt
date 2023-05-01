package com.example.roomdemo.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.database.models.TaskItem
import com.example.roomdemo.R
import com.example.roomdemo.activities.AddTask

class TaskListAdapter(private val data: List<TaskItem>,private val context:Context) :
    RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitleTextView: TextView = view.findViewById(R.id.taskTitleText)
        val editIconView: ImageButton = view.findViewById(R.id.editIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.task_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskTitleTextView.text = data[position].title
        holder.editIconView.setOnClickListener {
            val item = data[position]
            Intent(context,AddTask::class.java).let{
                val instance = TaskItem(item.id,item.title)
                it.putExtra("task",instance)
                context.startActivity(it)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}