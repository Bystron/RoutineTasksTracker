package com.routinetaskstracker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.routinetaskstracker.NewTaskDialogFragment
import com.routinetaskstracker.R
import com.routinetaskstracker.databinding.FragmentListsBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentListsBinding? = null
    private val binding get() = _binding!!

    private val todoListItems = mutableListOf<String>()
    private lateinit var listAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListsBinding.inflate(inflater, container, false)

        // Initialize ListView and adapter
        listAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, todoListItems)
        binding.listView.adapter = listAdapter

        // Add task button click listener
        binding.addTaskButton.setOnClickListener {
            showNewTaskUI()
        }

        // Listen for results from NewTaskDialogFragment
        parentFragmentManager.setFragmentResultListener(
            NewTaskDialogFragment.REQUEST_KEY,
            viewLifecycleOwner
        ) { _, result ->
            val taskName = result.getString(NewTaskDialogFragment.RESULT_KEY)
            if (!taskName.isNullOrEmpty()) {
                addTaskToList(taskName)
                Snackbar.make(binding.root, "Task Added: $taskName", Snackbar.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun showNewTaskUI() {
        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialogue)
        newFragment.show(parentFragmentManager, "newtask")
    }

    private fun addTaskToList(task: String) {
        todoListItems.add(task)
        listAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
