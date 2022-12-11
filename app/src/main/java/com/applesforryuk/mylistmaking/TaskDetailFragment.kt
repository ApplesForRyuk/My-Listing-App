package com.applesforryuk.mylistmaking

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.telecom.Call
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.FragmentTaskDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TaskDetailFragment : Fragment() {
    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var list: TaskList
    lateinit var addTaskButton: FloatingActionButton
    lateinit var listDataManager: ListDataManager

    private var adapter: TaskListAdapter? = null

    private lateinit var callback: Callback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listDataManager = ViewModelProviders.of(this)[ListDataManager::class.java]
        arguments?.let {
            val args = TaskDetailFragmentArgs.fromBundle(it)
            list = listDataManager.readLists().filter { list -> list.name == args.listString }[0]
        }

        activity?.let {
            binding.taskListRecyclerview.layoutManager = LinearLayoutManager(it)
            adapter = TaskListAdapter(list)
            binding.taskListRecyclerview.adapter = adapter

            callback.updateToolbarTitle(list.name)

            addTaskButton = view.findViewById(R.id.add_task_button)
            addTaskButton.setOnClickListener {
                showCreateTaskDialog()
            }
        }
    }

    private fun showCreateTaskDialog() {
        activity?.let {
            val taskEditText = EditText(it)
            taskEditText.inputType = InputType.TYPE_CLASS_TEXT
            AlertDialog.Builder(it)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task) { dialog, _ ->
                    val task = taskEditText.text.toString()
                    list.tasks.add(task)
                    listDataManager.saveList(list)
                    dialog.dismiss()
                }
                .create()
                .show()
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = context as Callback
    }
}
