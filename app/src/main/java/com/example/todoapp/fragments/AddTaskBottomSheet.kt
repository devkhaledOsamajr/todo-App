package com.example.todoapp.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todoapp.callbacks.OnTaskAddedListner
import com.example.todoapp.database.TaskDatabase
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.FragmentAddTasksBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar

class AddTaskBottomSheet : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTasksBinding
    lateinit var calendar: Calendar
    var onTaskAddedListner: OnTaskAddedListner? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddTasksBinding.inflate(layoutInflater)
        return binding.root
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendar = Calendar.getInstance()


        binding.addTaskTime.setOnClickListener {
            showDatePicker()
        }

        binding.btnAddTask.setOnClickListener {
            if (validatefields()) {

                val task = Task(
                    title = binding.taskEditText.text.toString(),
                    description = binding.taskDetailEditText.text.toString(),
                    date = calendar.time,
                    isDone = false
                )
                TaskDatabase.getInstance(requireContext()).getTaskDao().insertTask(task)
                onTaskAddedListner?.onTaskAdded()
                dismiss()
                Toast.makeText(requireContext(), "Task Added Successfully", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }


    private fun validatefields(): Boolean {
        if (binding.taskEditText.text.isNullOrEmpty() || binding.taskEditText.text.isNullOrBlank()) {
            binding.taskEditText.error = "Required"
            return false
        } else
            binding.taskEditText.error = null

        if (binding.taskDetailEditText.text.isNullOrEmpty() || binding.taskDetailEditText.text.isNullOrBlank()) {
            binding.taskDetailEditText.error = "Required"
            return false
        } else
            binding.taskDetailEditText.error = null





        return true
    }
}
