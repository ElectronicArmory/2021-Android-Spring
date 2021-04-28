package edu.boisestate.getitdone.models

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToOne
import java.io.Serializable

@Entity
data class ToDoItem(
    @Id var id:Long = 0,

    @SerializedName("todoTitle")
    var todoTitle: String?,
    var todoDescription: String?,
    var isDone:Boolean?,
    var todoDueDate: String?,
    var todoPriority: Int?
):Serializable{
    lateinit var storeToBePurchased: ToOne<StoreModel>
}