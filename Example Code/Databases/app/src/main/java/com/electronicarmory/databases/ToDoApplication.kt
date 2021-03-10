package com.electronicarmory.databases

import android.app.Application

class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        ObjectBox.init(this)
    }
}