package com.example.todoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.ActivityHomeBinding
import com.example.todoapp.fragments.AddTaskBottomSheet
import com.example.todoapp.fragments.fragmentSetttings
import com.example.todoapp.fragments.fragmentTasks

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_task -> {
                    pushFragment(fragmentTasks())
                }

                R.id.navigation_setting -> {
                    pushFragment(fragmentSetttings())
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.navigation_task
        binding.fabAddTask.setOnClickListener {
            val bottomSheet = AddTaskBottomSheet()
            bottomSheet.show(supportFragmentManager, "")
        }

    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            .commit()
    }
}