package com.applesforryuk.mylistmaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.applesforryuk.mylistmaking.databinding.ActivityDetailBinding
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding
import com.applesforryuk.mylistmaking.databinding.TaskViewHolderBinding


class DetailActivity : AppCompatActivity() {

    private var list: TaskList? = null

    private lateinit var binding: ActivityDetailBinding






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)


        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList?
        title = list?.name

        binding.taskListRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.taskListRecyclerview.adapter = list?.let { TaskListAdapter(it) }



    }
}