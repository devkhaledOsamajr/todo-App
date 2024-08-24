package com.example.todoapp.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.ItemTasksBinding
import java.text.SimpleDateFormat

class TaskAdapter(private var taskList: MutableList<Task>) : Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: ItemTasksBinding) : ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            val dateFormattedAsString = SimpleDateFormat("yyyy/MM/dd").format(task.date)
            binding.time.text = dateFormattedAsString

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = ItemTasksBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList.get(position)
        holder.bind(task)


        onDeleteClick.let {
            holder.binding.leftView.setOnClickListener {
                onDeleteClick?.invoke(position, task)
            }
        }

    }
    fun deleteTask(task: Task, position: Int) {
        this.taskList.remove(task)
        notifyItemRemoved(position)
    }




    var onDeleteClick: ((Int, Task) -> Unit)? = null


}