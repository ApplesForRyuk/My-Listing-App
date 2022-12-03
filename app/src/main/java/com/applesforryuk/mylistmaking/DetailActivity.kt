package com.applesforryuk.mylistmaking

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.ActivityDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var list: TaskList? = null
    lateinit var addTaskButton: FloatingActionButton

    private var adapter: TaskListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as? TaskList
        title = list?.name

        binding.taskListRecyclerview.layoutManager = LinearLayoutManager(this)
        adapter = list?.let { TaskListAdapter(it) }
        binding.taskListRecyclerview.adapter = adapter

        addTaskButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener{
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) {
                    dialog, _ ->
                val task = taskEditText.text.toString()
                list?.tasks?.add(task)
                dialog.dismiss()
            }
            .create()
            .show()

    }
}
