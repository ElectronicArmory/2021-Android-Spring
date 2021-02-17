package com.electronicarmory.observerpattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        val launchButton = findViewById<Button>(R.id.launchButton)
        launchButton.setOnClickListener {
            val intent = Intent(this, BroadcastActivity::class.java)
            startActivity(intent)
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event:RadioEvent){
        Log.d("BSU", "${event.radioData}")

        val mainTextView = findViewById<TextView>(R.id.mainTextView)
        mainTextView.text = event.radioData
    }


    override fun onDestroy() {
        EventBus.getDefault().unregister(this)

        super.onDestroy()
    }
}