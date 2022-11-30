package com.applesforryuk.mylistmaking

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListViewHolder>() {

    private var  toDoLists = mutableListOf("Android Development", "House work", "Errands")


    fun addNewItem(listName: String = "") {
        if (listName.isEmpty()) {
            toDoLists.add("Todo List" + (toDoLists.size +1))
        } else {
            toDoLists.add(listName)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false )
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {

        return toDoLists.size
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.listPositionTextView.text = (position + 1).toString()
        holder.listPositionTextView.text = toDoLists[position]
    }


}