package com.applesforryuk.mylistmaking

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter(private val lists: ArrayList<TaskList>, private val clickListener: TodoListClickListener) :
    RecyclerView.Adapter<ToDoListViewHolder>() {
    interface TodoListClickListener {
        fun listItemClicked(list: TaskList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return ToDoListViewHolder(view)



    }

    override fun getItemCount(): Int {
        return lists.size


    }

//    private fun toggleStrikeThrough(listTitleTextView: TextView, isChecked: Boolean) {
//        if (isChecked) {
//            listTitleTextView.paintFlags   = listTitleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
//        } else {
//            listTitleTextView.paintFlags  = listTitleTextView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
//        }
//    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.listPositionTextView?.text = (position + 1).toString()
        holder.listPositionTextView?.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
//        val isDone: Boolean = holder.checkedTodo.isChecked

        holder.checkedTodo.setOnClickListener {
            if (holder.checkedTodo.isChecked) {
                holder.listPositionTextView?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }


        }
    }


    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }
}

