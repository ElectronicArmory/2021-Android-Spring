package com.electronicarmory.secondactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("BSU", savedInstanceState.toString())

        val launchButton = findViewById<Button>(R.id.launchButton)
        launchButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("SomeString", "This is the string from the first activity")

            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }


    override fun onStop() {
        super.onStop()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}