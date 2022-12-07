package com.applesforryuk.mylistmaking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding

    private var toDoListFragment = ToDoListFragment.newInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        Navigation.findNavController(this, R.id.nav_host_fragment)



        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, toDoListFragment)
            .commit()

    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        binding.toolbar.title = "Listmaker"
    }











}
