package com.applesforryuk.mylistmaking

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.PopupMenu
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


//        view.setOnLongClickListener {
//            val menu = PopupMenu(it.context, it)
//            menu.inflate(R.menu.option_menu)
//            menu.setOnMenuItemClickListener { item ->
//                when (item.itemId) {
//                    R.id.delete_option -> {
//                        val position =
//                        lists.removeAt(position)
//                        notifyItemRemoved(position)
//                        true
//                    }
//                    R.id.edit_option -> {
//                        // TODO: handle edit menu item click
//                        true
//                    }
//                    else -> false
//                }
//            }
//            menu.show()
//            true
//
//
//        }
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
        holder.itemView.apply{
            holder.checkedTodo.setOnClickListener {
                if (holder.checkedTodo.isChecked) {
                    holder.listPositionTextView?.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else {
                    holder.listPositionTextView?.paintFlags = Paint.ANTI_ALIAS_FLAG
                }
            }
        }

        holder.itemView.setOnLongClickListener {
            showPopupMenu(it, position)
            true
        }

    }

    private fun showPopupMenu(view: View, position: Int) {
        PopupMenu(view.context, view).apply {
            inflate(R.menu.option_menu)
            setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.delete_option -> {
                        lists.removeAt(position)
                        notifyItemRemoved(position)
                        true
                    }
                    R.id.edit_option -> {
                        editList(position)
                        true
                    }
                    else -> false
                }
            }
            show()
        }
    }

    private fun editList(position: Int) {
        val list = lists[position]

    }


    fun addList(list: TaskList) {
        lists.add(list)
        notifyItemInserted(lists.size - 1)
    }
}

