package com.applesforryuk.mylistmaking

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.FragmentTaskDetailBinding
import com.applesforryuk.mylistmaking.databinding.FragmentToDoListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TaskDetailFragment : Fragment(){

    lateinit var list: TaskList
    lateinit var addTaskButton: FloatingActionButton
    lateinit var listDataManager: ListDataManager

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!
    private var adapter: TaskListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getParcelable(ARG_LIST)!!
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let{
            binding.taskListRecyclerview.layoutManager = LinearLayoutManager(it)
            adapter = list?.let { TaskListAdapter(it) }
            binding.taskListRecyclerview.adapter = adapter

            addTaskButton = view.findViewById(R.id.add_task_button)
            addTaskButton.setOnClickListener {
                showCreateTaskDialog()

        }


        }
        listDataManager = ViewModelProviders.of(this)[ListDataManager::class.java]
    }

    private fun showCreateTaskDialog() {
        activity?.let {
            val taskEditText = EditText(it)
            taskEditText.inputType = InputType.TYPE_CLASS_TEXT
            AlertDialog.Builder(it)
                .setTitle(R.string.task_to_add)
                .setView(taskEditText)
                .setPositiveButton(R.string.add_task) {
                        dialog, _ ->
                    val task = taskEditText.text.toString()
                    list?.tasks?.add(task)
                    dialog.dismiss()
                }
                .create()
                .show()
        }


    }

    companion object {

        private const val ARG_LIST = "list"


        fun newInstance(list: TaskList): TaskDetailFragment {

            val bundle = Bundle()

            bundle.putParcelable(ARG_LIST, list)
            val fragment = TaskDetailFragment()
            fragment.arguments = bundle
            return fragment
        }

                }




}