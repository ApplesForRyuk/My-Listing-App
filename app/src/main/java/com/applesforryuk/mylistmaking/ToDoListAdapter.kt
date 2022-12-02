package com.applesforryuk.mylistmaking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(private val lists: ArrayList<TaskList>, private val clickListener: TodoListClickListener) : RecyclerView.Adapter<ToDoListViewHolder>() {
    interface TodoListClickListener {
        fun listItemClicked(list: TaskList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false )
        return ToDoListViewHolder(view)
    }

    override fun getItemCount(): Int {

        return lists.size

    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {

        holder.listPositionTextView!!.text = (position + 1).toString()
        holder.listPositionTextView!!.text = lists[position].name
        holder.itemView.setOnClickListener{
            clickListener.listItemClicked(lists[position])
        }
    }

    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size-1)

    }


}