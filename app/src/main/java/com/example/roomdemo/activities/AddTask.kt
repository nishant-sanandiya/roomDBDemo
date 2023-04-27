package com.example.roomdemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.roomdemo.databinding.ActivityAddTaskBinding
import com.example.roomdemo.utils.KeyboardUtils

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
            binding.addTaskButton ->{

            }
        }
        v?.let { KeyboardUtils.hideKeyboard(this, it) }
    }
}