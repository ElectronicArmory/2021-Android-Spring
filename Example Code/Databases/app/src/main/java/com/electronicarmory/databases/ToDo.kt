package com.electronicarmory.databases

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ToDo(
    @Id
    var id:Long = 0,
    var title:String = "New Todo",
    var description:String = "",
    val isDone:Boolean = false,
    var dueDate:String = "Monday")
