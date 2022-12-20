package com.applesforryuk.mylistmaking

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var listPositionTextView = itemView?.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView?.findViewById<TextView>(R.id.itemString)
    var checkedTodo = itemView?.findViewById<CheckBox>(R.id.checked_unchecked)










}