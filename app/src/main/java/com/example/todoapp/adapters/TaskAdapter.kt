package com.example.todoapp.adapters


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.todoapp.R
import com.example.todoapp.database.model.Task
import com.example.todoapp.databinding.ItemTasksBinding
import java.text.SimpleDateFormat

class TaskAdapter(private var taskList: MutableList<Task>) : Adapter<TaskAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: ItemTasksBinding) : ViewHolder(binding.root) {
        private fun changeTaskStatus(isDone: Boolean) {
            if (isDone) {
                binding.draggingBar.setImageResource(R.drawable.draggin_bar_done)
                binding.taskTitle.setTextColor(Color.GREEN)
                binding.btnTaskIsDone.setBackgroundResource(R.drawable.done_)
            } else {
                val blue = ContextCompat.getColor(itemView.context, R.color.blue)
                binding.draggingBar.setImageResource(R.drawable.dragging_bar)
                binding.taskTitle.setTextColor(blue)
                binding.btnTaskIsDone.setBackgroundResource(R.drawable.check_mark)

            }
        }

        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            val dateFormattedAsString = SimpleDateFormat("yyyy/MM/dd").format(task.date)
            binding.time.text = task.time
            changeTaskStatus(task.isDone)

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
        val task = taskList[position]
        holder.bind(task)


        onDeleteClick?.let {
            holder.binding.leftView.setOnClickListener {
                onDeleteClick?.invoke(position, task)
            }
        }
        onDoneBtnClickListner?.let {
            holder.binding.btnTaskIsDone.setOnClickListener {
                onDoneBtnClickListner?.invoke(position, task)
            }
        }


    }

    fun deleteTask(task: Task, position: Int) {
        this.taskList.remove(task)
        notifyItemRemoved(position)
    }

    fun updateTask(task: Task, position: Int) {
        this.taskList.set(position, task)
        notifyItemChanged(position)
    }


    var onDeleteClick: ((Int, Task) -> Unit)? = null
    var onDoneBtnClickListner: ((Int, Task) -> Unit)? = null
    var onItemClickListner: ((Int, Task) -> Unit)? = null

}