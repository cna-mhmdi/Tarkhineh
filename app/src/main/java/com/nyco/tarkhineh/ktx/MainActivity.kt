package com.nyco.tarkhineh.ktx

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sharedPreferences = this.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
//        val accessToken = sharedPreferences.getString("access_token", null)
//        val refreshToken = sharedPreferences.getString("refresh_token", null)
//
//        Toast.makeText(this, "access token is : $accessToken", Toast.LENGTH_SHORT).show()
//        Toast.makeText(this, "refresh token is : $refreshToken", Toast.LENGTH_SHORT).show()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<BottomNavigationView>(R.id.nav_view)?.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}