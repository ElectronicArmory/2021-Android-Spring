package com.electronicarmory.secondactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val someDataFromIntent = intent.getStringExtra("SomeString")
//        val textView = findViewById<TextView>(R.id.textView2)
//        textView.text = someDataFromIntent


    }
}