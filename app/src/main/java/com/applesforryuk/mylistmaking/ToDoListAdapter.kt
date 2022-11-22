package com.applesforryuk.mylistmaking

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ToDoListAdapter : RecyclerView.Adapter<ToDoListViewHolder>() {

    private val  toDoLists = arrayOf("Android Development", "House work", "Errands")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
        return toDoLists.size
    }
}