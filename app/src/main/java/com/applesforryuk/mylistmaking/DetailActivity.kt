package com.applesforryuk.mylistmaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class DetailActivity : AppCompatActivity() {

    private var list: TaskList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList?
        title = list?.name

    }
}