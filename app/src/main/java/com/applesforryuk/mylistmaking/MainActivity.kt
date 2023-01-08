package com.applesforryuk.mylistmaking

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.applesforryuk.mylistmaking.databinding.ActivityMainBinding

class MainActivity :
    AppCompatActivity(),
    Callback {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        findNavController(R.id.nav_host_fragment)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateToolbarTitle(getString(R.string.app_name))
    }

    override fun updateToolbarTitle(title: String) {
        binding.toolbar.title = title
    }
}

interface Callback {
    fun updateToolbarTitle(title: String)
}
