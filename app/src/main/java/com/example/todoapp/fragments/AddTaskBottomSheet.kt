package com.example.todoapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.example.todoapp.databinding.FragmentAddTasksBinding
import com.example.todoapp.databinding.FragmentTasksBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTasksBinding
    lateinit var calendar: Calendar
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        calendar = Calendar.getInstance()
        binding = FragmentAddTasksBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addTaskTime.setOnClickListener {
            showDatePicker()
        }
        binding.btnAddTask.setOnClickListener {
            if (validatefields())
                Toast.makeText(requireContext(), "fields validated", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(requireContext())
        datePickerDialog.setOnDateSetListener { view, year, month, dayOfMonth ->

            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.addTaskTimeValue.text = "$dayOfMonth/${month + 1}/$year"
        }


        datePickerDialog.show()

    }

    private fun validatefields(): Boolean {
        if (binding.taskEditText.text.isNullOrEmpty() || binding.taskEditText.text.isNullOrEmpty()) {
            binding.taskEditText.error = "Required"
            return false
        } else
            binding.taskEditText.error = null

        if (binding.taskDetailEditText.text.isNullOrEmpty() || binding.taskDetailEditText.text.isNullOrEmpty()) {
            binding.taskDetailEditText.error = "Required"
            return false
        } else
            binding.taskDetailEditText.error = null





        return true
    }
}
