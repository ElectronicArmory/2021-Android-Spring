package com.electronicarmory.fragmentbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    // Instantiate Fragment
    private val imageCyclerFragment = ImageCyclerFragment.newInstance("","")
    private val secondFragment = SecondFragment.newInstance("","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Place the fragment into the container
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, imageCyclerFragment)
            .commit()

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, secondFragment)
                .addToBackStack(secondFragment.toString())
                .commit()
        }

        val previousButton = findViewById<Button>(R.id.previousButton)
        previousButton.setOnClickListener {
            Log.d("BSU", secondFragment.toString())
            supportFragmentManager.popBackStack()
        }

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            imageCyclerFragment.nextImage()
        }
    }
}