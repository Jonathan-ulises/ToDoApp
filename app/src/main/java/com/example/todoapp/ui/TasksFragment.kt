package com.example.todoapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.todoapp.R
import com.example.todoapp.core.Result
import com.example.todoapp.data.remote.TaskDataSource
import com.example.todoapp.databinding.FragmentTasksBinding
import com.example.todoapp.domain.TaskRepositoryImpl
import com.example.todoapp.presentation.TaskViewModelFactory
import com.example.todoapp.presentation.TasksViewModel

class TasksFragment : Fragment(R.layout.fragment_tasks) {

    private lateinit var binding: FragmentTasksBinding
    private val viewModel by viewModels<TasksViewModel> {
        TaskViewModelFactory(
            TaskRepositoryImpl(
                TaskDataSource()
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTasksBinding.bind(view)

        viewModel.fetchAllTask().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {}
                is Result.Success -> {
                    binding.rvTask.adapter = TaskAdapter(result.data)
                }

                is Result.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        "Error: ${result.exception}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}