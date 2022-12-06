package com.applesforryuk.mylistmaking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private var toDoListFragment = ToDoListFragment.newInstance()




    companion object {
        const val INTENT_LIST_KEY = "list"
        const val LIST_DETAIL_REQUEST_CODE = 50
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)



        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, toDoListFragment)
            .commit()

    }











}
