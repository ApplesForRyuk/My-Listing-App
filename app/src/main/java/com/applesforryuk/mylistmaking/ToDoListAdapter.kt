package com.applesforryuk.mylistmaking

import android.graphics.Color
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


    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.listPositionTextView?.text = (position + 1).toString()
        holder.listPositionTextView?.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
//        val isDone: Boolean = holder.checkedTodo.isChecked
        holder.itemView.apply{
            holder.checkedTodo.setOnClickListener {
                if (holder.checkedTodo.isChecked) {
                    holder.listPositionTextView?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
//                    holder.listPositionTextView?.setTextColor(Color.MAGENTA)
                    holder.listPositionTextView?.paintFlags = Paint.ANTI_ALIAS_FLAG
                }


            }

        }


    }


    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }
}

