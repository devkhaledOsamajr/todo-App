package com.example.todoapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoapp.callbacks.OnTaskAddedListner
import com.example.todoapp.database.TaskDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.FragmentAddTasksBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTasksBinding
    lateinit var calendar: Calendar
    var onTaskAddedListner: OnTaskAddedListner? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTasksBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun showTimePicker() {
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_12H) // or TimeFormat.CLOCK_24H
            .setHour(12) // Set default hour
            .setMinute(0) // Set default minute
            .setTitleText("Select Task Time")
            .build()

        picker.show(parentFragmentManager, "MATERIAL_TIME_PICKER")

        picker.addOnPositiveButtonClickListener {
            val selectedHour = picker.hour
            val selectedMinute = picker.minute
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.addTaskTimeValue.text = formattedTime
        }
    }

    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(requireContext())
        datePickerDialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.addTaskTimeValue.text = "$dayOfMonth/${month + 1}/$year"
        }
        datePickerDialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()

        binding.addTaskTime.setOnClickListener {
            showTimePicker()
        }

        binding.btnAddTask.setOnClickListener {
            if (validateFields()) {
                val task = Task(
                    title = binding.taskEditText.text.toString(),
                    description = binding.taskDetailEditText.text.toString(),
                    date = calendar.time,
                    time = binding.addTaskTimeValue.text.toString(),
                    isDone = false
                )
                TaskDatabase.getInstance(requireContext()).getTaskDao().insertTask(task)
                onTaskAddedListner?.onTaskAdded()
                dismiss()
            }
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.taskEditText.text.isNullOrEmpty()) {
            binding.taskEditText.error = "Required"
            isValid = false
        } else {
            binding.taskEditText.error = null
        }

        if (binding.taskDetailEditText.text.isNullOrEmpty()) {
            binding.taskDetailEditText.error = "Required"
            isValid = false
        } else {
            binding.taskDetailEditText.error = null
        }

        return isValid
    }
}
