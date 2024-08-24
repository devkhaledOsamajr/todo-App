package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapp.callbacks.OnTaskAddedListner
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.fragments.AddTaskBottomSheet
import com.example.todoapp.fragments.fragmentSetttings

import fragmentTasks

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var taskFragment: fragmentTasks
    lateinit var settingFragment: fragmentSetttings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskFragment = fragmentTasks()
        settingFragment = fragmentSetttings()
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_task -> {
                    pushFragment(taskFragment)
                }

                R.id.navigation_setting -> {
                    pushFragment(settingFragment)
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.navigation_task
        binding.fabAddTask.setOnClickListener {
            val bottomSheet = AddTaskBottomSheet()
            bottomSheet.onTaskAddedListner = object : OnTaskAddedListner {
                override fun onTaskAdded() {
                    if (taskFragment.isVisible) {
                        taskFragment.getAllTasks()
                    }
                }
            }
            bottomSheet.show(supportFragmentManager, "")

        }

    }


    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}