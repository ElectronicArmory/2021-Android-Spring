package edu.boisestate.getitdone

import android.util.Log
import edu.boisestate.getitdone.models.StoreModel

import edu.boisestate.getitdone.models.ToDoItem
import edu.boisestate.getitdone.models.ToDoItem_
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.query
import io.objectbox.query.QueryBuilder
import io.objectbox.relation.ToOne
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

object ToDoManager {
    private val mToDoList = mutableListOf<ToDoItem>()
    private val mToDoFromServer = mutableListOf<ToDoItem>()

    private val todoBox: Box<ToDoItem> = ObjectBox.boxStore.boxFor(ToDoItem::class.java)
    private val storeModelBox: Box<StoreModel> = ObjectBox.boxStore.boxFor(StoreModel::class.java)

    private val query = todoBox.query {
        order(ToDoItem_.todoPriority, QueryBuilder.DESCENDING)
        order(ToDoItem_.todoDueDate, QueryBuilder.DESCENDING)
        equal(ToDoItem_.isDone, false)
//        greater()
    }

    init {
        val results = query.find()
        mToDoList.addAll( results )
    }

    private val callback = object: Callback<List<ToDoItem>>{
        override fun onFailure(call: Call<List<ToDoItem>>, t: Throwable) {
            Log.e("BSU", "Something happened:  ${call.toString()}")
        }

        override fun onResponse(call: Call<List<ToDoItem>>, response: Response<List<ToDoItem>>) {
            if( response?.isSuccessful()){
                Log.d("BSU", response.body()!!.toString())
                addToDoItems(response.body()!!)
                mToDoFromServer.clear()
                mToDoFromServer.addAll(response.body()!!)
                EventBus.getDefault().post(NewTodoItemsEvent())
            }
        }
    }

    fun updateToDos(){
        WebServices.todos(callback)
    }

    fun todoList(): List<ToDoItem> {
        val results = query.find()
        mToDoList.clear()
        mToDoList.addAll(mToDoFromServer)
        mToDoList.addAll(results)

        return mToDoList
    }

    fun addToDoItem( toDoTitle:String, toDoDescription:String = "", toDoDueDate:String = "", toDoPriority:Int = 9){
        var dueDate:Date
        if (toDoDueDate.isBlank()){
            dueDate = Date()
        }
        else {
            dueDate = Date(toDoDueDate)
        }

        val groceryStore = StoreModel(storeName = "Grocery Store is US", storeNumber = "123ABC")
        storeModelBox.put(groceryStore)

        val newToDoItem = ToDoItem(
            todoTitle = toDoTitle,
            todoDescription = toDoDescription,
            isDone = false,
            todoDueDate = "",
            todoPriority = toDoPriority
        )

        addToDoItem(newToDoItem)

        todoBox.put(newToDoItem)

        EventBus.getDefault().post(NewTodoItemsEvent())
    }

    private fun addToDoItem(newItem: ToDoItem) {
        val groceryStore = StoreModel(storeName = "Grocery Store is US", storeNumber = "123ABC")
        storeModelBox.put(groceryStore)

        newItem.storeToBePurchased.target = groceryStore
        todoBox.put(newItem)
        mToDoList.add(newItem)
        EventBus.getDefault().post(NewTodoItemsEvent())
    }

    fun addToDoItems( listOfToDos:List<ToDoItem> ){
        mToDoList.addAll(listOfToDos)

        mToDoList.sortBy {
            it.todoPriority
        }

        EventBus.getDefault().post(NewTodoItemsEvent())
    }

    /*
    *
     */
    fun removeToDo( todoToRemove:ToDoItem ){
        mToDoList.remove(todoToRemove)
    }

    fun saveToDo(todoItem:ToDoItem){
        todoBox.put(todoItem)
    }

}