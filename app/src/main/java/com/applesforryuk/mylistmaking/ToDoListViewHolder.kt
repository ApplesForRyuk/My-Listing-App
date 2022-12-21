package com.applesforryuk.mylistmaking

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    var listPositionTextView = itemView?.findViewById<TextView>(R.id.itemNumber)
    var listTitleTextView = itemView?.findViewById<TextView>(R.id.itemString)
    var checkedTodo: CheckBox = itemView.findViewById<CheckBox>(R.id.checking_box)
    var deleteOption: TextView? = itemView.findViewById<TextView>(R.id.delete_option)
    var editOption: TextView? = itemView.findViewById<TextView>(R.id.edit_option)



}