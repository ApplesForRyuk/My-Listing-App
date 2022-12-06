package com.applesforryuk.mylistmaking

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.FragmentToDoListBinding


class ToDoListFragment : Fragment(), ToDoListAdapter.TodoListClickListener {

    private var _binding: FragmentToDoListBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!


    private var listener: OnFragmentInteractionListener? = null

    private lateinit var listDataManager: ListDataManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToDoListBinding.inflate(inflater, container, false)
        return binding.root
    }

    /*override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    } */




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lists = listDataManager.readLists()



        binding.listsRecyclerview.layoutManager = LinearLayoutManager(activity)
        binding.listsRecyclerview.adapter = ToDoListAdapter(lists, this)




    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
            listDataManager = ListDataManager(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onTodoListClicked(list: TaskList)
    }

    companion object {
        fun newInstance(): ToDoListFragment {
            return ToDoListFragment()
        }
    }

    override fun listItemClicked(list: TaskList) {
        listener?.onTodoListClicked(list)
    }

    fun addList(list: TaskList) {
        listDataManager.saveList(list)
        val toDoAdapter = binding.listsRecyclerview.adapter as ToDoListAdapter
        toDoAdapter.addList(list)
    }

    fun saveList(list: TaskList?) {
        list?.let { it1 -> listDataManager.saveList(it1) }
        updateLists()
    }

    private fun updateLists() {
        val lists = listDataManager.readLists()
        binding.listsRecyclerview.adapter = ToDoListAdapter(lists,this)

    }
}