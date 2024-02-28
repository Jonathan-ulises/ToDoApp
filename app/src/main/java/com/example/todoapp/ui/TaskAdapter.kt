package com.example.todoapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.core.BaseViewHolder
import com.example.todoapp.data.model.Task
import com.example.todoapp.databinding.TaskItemViewBinding

class TaskAdapter(private val taskList: List<Task>): RecyclerView.Adapter<BaseViewHolder<Task>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Task> {
        val itemBinding = TaskItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = taskList.size

    override fun onBindViewHolder(holder: BaseViewHolder<Task>, position: Int) {
        holder.bind(taskList[position])
    }

    private inner class TaskViewHolder(
        val binding: TaskItemViewBinding
    ): BaseViewHolder<Task>(binding.root) {
        override fun bind(item: Task) {
            binding.txtTitle.text = item.title
            binding.txtCreatedAt.text = item.createdAt.toString()
        }
    }
}