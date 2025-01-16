package com.routinetaskstracker

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.routinetaskstracker.databinding.DialogNewTaskBinding

class NewTaskDialogFragment : DialogFragment() {

    companion object {
        const val REQUEST_KEY = "new_task_request_key"
        const val RESULT_KEY = "new_task_result_key"
        fun newInstance(title: Int): NewTaskDialogFragment {
            val args = Bundle()
            args.putInt("dialog_title", title)
            val fragment = NewTaskDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: DialogNewTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogNewTaskBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireContext())
        val title = arguments?.getInt("dialog_title")
        title?.let { builder.setTitle(it) }

        builder.setView(binding.root)
            .setPositiveButton(R.string.save) { _, _ ->
                val taskName = binding.tasknameText.text.toString().trim()
                if (taskName.isNotEmpty()) {
                    parentFragmentManager.setFragmentResult(
                        REQUEST_KEY,
                        Bundle().apply { putString(RESULT_KEY, taskName) }
                    )
                }
            }
            .setNegativeButton(android.R.string.cancel, null)

        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
