package com.applesforryuk.mylistmaking

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(val lists: ArrayList<TaskList>) : RecyclerView.Adapter<ToDoListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false )
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {

        return lists.size

    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.listPositionTextView.text = (position + 1).toString()
        holder.listPositionTextView.text = lists[position].name
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)

    }


}