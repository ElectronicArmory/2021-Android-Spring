package com.electronicarmory.databases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val currentToDo = ToDo(title = "Get groceries", description = "This is the grocery description", isDone = false)

        val todoBox: Box<ToDo> = ObjectBox.boxStore.boxFor()

        val query = todoBox.query {
            equal(ToDo_.isDone, false)
            order(ToDo_.title, QueryBuilder.DESCENDING)
        }
//        todoBox.put(currentToDo)
//        val results = todoBox.all

        val results = query.find()
        Log.d("BSU", "${results.size}")
    }
}