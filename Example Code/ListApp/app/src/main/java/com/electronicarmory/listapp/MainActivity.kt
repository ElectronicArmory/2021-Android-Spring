package com.electronicarmory.listapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var restaurantFragment: RestaurantFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restaurantFragment = RestaurantFragment()

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, restaurantFragment)
                .commit()
    }
}