package com.applesforryuk.mylistmaking

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.FragmentToDoListBinding


class ToDoListFragment : Fragment(), ToDoListAdapter.TodoListClickListener {
    private var _binding: FragmentToDoListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var listDataManager: ListDataManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            listDataManager = ViewModelProviders.of(this)[ListDataManager::class.java]
        }

        val lists = listDataManager.readLists()
//        val bottomSheet = binding.bottomSheet
//        val behaviorOfBottomSheet = BottomSheetBehavior.from(bottomSheet)


        binding.listsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.listsRecyclerview.adapter = ToDoListAdapter(lists, this)

        binding.fab.setOnClickListener {
            showCreateToDoListDialog()
        }

    }

    override fun listItemClicked(list: TaskList) {
        showTaskListItems(list)
    }

    private fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val toDoAdapter = binding.listsRecyclerview.adapter as ToDoListAdapter
        toDoAdapter.addList(list)
    }

    private fun deleteList(list: TaskList) {
        listDataManager.saveList(list)
        val toDoAdapter = binding.listsRecyclerview.adapter as ToDoListAdapter
        toDoAdapter.addList(list)
    }

    fun saveList(list: TaskList?) {
        list?.let { it1 -> listDataManager.saveList(it1) }
        updateLists()
    }

    private fun showCreateToDoListDialog() {
        activity?.let {
            val dialogTitle = getString(R.string.name_of_list)
            val positiveButtonTitle = getString(R.string.create_list)
            val myDialog = AlertDialog.Builder(it)
            val toDoTitleEditText = EditText(it)
            toDoTitleEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS

            myDialog.setTitle(dialogTitle)
            myDialog.setView(toDoTitleEditText)

            myDialog.setPositiveButton(positiveButtonTitle) { dialog, _ ->

                val list = TaskList(toDoTitleEditText.text.toString())

                addList(list)


                dialog.dismiss()
                //after dismissing dialog user is brought to the new activity
                showTaskListItems(list)
            }
            myDialog.create().show()
        }
    }

    private fun showTaskListItems(list: TaskList) {
        view?.let {
            val action = ToDoListFragmentDirections.actionTodoListFragmentToTaskDetailFragment(list.name)
            it.findNavController().navigate(action)
        }
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        binding.listsRecyclerview.adapter = ToDoListAdapter(lists, this)
    }

}
