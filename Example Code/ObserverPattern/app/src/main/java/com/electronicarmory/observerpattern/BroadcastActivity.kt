package com.electronicarmory.observerpattern

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import java.util.*

class BroadcastActivity : AppCompatActivity() {
    lateinit var broadcastButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        broadcastButton = findViewById(R.id.broadcastButton)

        broadcastButton.setOnClickListener {
            val editTextTextPersonName = findViewById<EditText>(R.id.editTextTextPersonName)
            val name:String = editTextTextPersonName.text.toString()
            EventBus.getDefault().post(RadioEvent(name, Date()))
            finish()
        }
    }
}