package com.routinetaskstracker.ui.home

import android.app.Activity
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.routinetaskstracker.R
import com.routinetaskstracker.databinding.FragmentListsBinding
import com.routinetaskstracker.NewTaskDialogFragment

class HomeFragment : Fragment() , NewTaskDialogFragment.NewTaskDialogListener{

    private var _binding: FragmentListsBinding? = null
    var todoListItems = ArrayList<String>()
    private var listAdapter: ArrayAdapter<String>? = null
    private var listView: ListView? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentListsBinding.inflate(inflater, container, false)
        val root: View = binding.root



            val addTaskButton: FloatingActionButton = binding.addTaskButton
            addTaskButton.setOnClickListener(
            showNewTaskUI()
        )
            listView = binding.listView //findviewbyID list_view
            populateListView()
        return root
    }
    fun showNewTaskUI(): View.OnClickListener? {

        val newFragment = NewTaskDialogFragment.newInstance(R.string.add_new_task_dialogue)
        fragmentManager?.let { newFragment.show(it, "newtask") }
        return null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDialogPositiveClick(dialog: DialogFragment, task: String) {
        TODO("Not yet implemented")
        todoListItems.add(task)
        view?.notifyDataSetChanged() //call on the listAdapter to update the ListView
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        TODO("Not yet implemented")
    }
}

private fun populateListView() {
   // listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todoListItems) //errores -> inicio: listAdapter final: todoListitems
   // listView?.adapter = listAdapter
}

private fun Drawable.Callback?.notifyDataSetChanged() {
    TODO("Not yet implemented")

}

