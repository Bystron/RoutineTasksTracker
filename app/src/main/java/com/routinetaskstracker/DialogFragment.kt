package com.routinetaskstracker
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar

class NewTaskDialogFragment:DialogFragment() {

    interface NewTaskDialogListener{
        fun onDialogPositiveClick(dialog: DialogFragment, task: String)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    var newTaskDialogListener: NewTaskDialogListener? = null

    companion object {
        fun newInstance(title: Int): NewTaskDialogFragment {

            val newTaskDialogFragment = NewTaskDialogFragment()
            val args = Bundle()
            args.putInt("dialog_title", title)
            newTaskDialogFragment.arguments = args
            return newTaskDialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog { // 5
        val title = arguments?.getInt("dialog_title")
        val builder = AlertDialog.Builder(activity)
        title?.let { builder.setTitle(it) }

        val dialogView =
            activity?.layoutInflater?.inflate(R.layout.dialog_new_task, null)
        val task = dialogView?.findViewById<EditText>(R.id.tasknameText)

        builder.setView(dialogView)
            .setPositiveButton(R.string.save, { dialog, id ->
                newTaskDialogListener?.onDialogPositiveClick(this,
                    task?.text.toString());

                if (dialogView != null) {
                    Snackbar.make(dialogView, "Task Added Successfully", Snackbar.LENGTH_LONG).setAction("Action", null).show()
                }

            })
            .setNegativeButton(android.R.string.cancel, { dialog,
                                                          id ->
                newTaskDialogListener?.onDialogNegativeClick(this)
            })
        return builder.create()
    }

    override fun onAttach(activity: Activity) { // 6
        super.onAttach(activity)
        try {
            newTaskDialogListener = activity as NewTaskDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement NewTaskDialogListener")
        }
    }

}