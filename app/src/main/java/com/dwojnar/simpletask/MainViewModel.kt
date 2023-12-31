package com.dwojnar.simpletask

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.dwojnar.simpletask.models.Database
import com.dwojnar.simpletask.models.TodoTask
import com.google.gson.Gson
import java.io.File

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseFile = "database.json"
    private val context = getApplication<Application>().applicationContext

    val todoTasks = mutableStateListOf<TodoTask>()
    val currentTodoTask = mutableStateOf<TodoTask>(TodoTask("", "", false))

    fun setAllTodoTasksUndone(){
        val tempList = todoTasks.toList()
        tempList.forEach { x -> x.done = false }
        todoTasks.clear()
        todoTasks.addAll(tempList)
        var file = File(context.filesDir, databaseFile);
        val bufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val database = Gson().fromJson(inputString, Database::class.java)
        database.todoTasks.clear()
        database.todoTasks.addAll(todoTasks)
        val fileContents = Gson().toJson(database)
        context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
    }

    fun removeTodoTask(uid: String){
        var file = File(context.filesDir, databaseFile);
        val bufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val database = Gson().fromJson(inputString, Database::class.java)
        database.todoTasks.remove(database.todoTasks.first { it.uid == uid })
        val fileContents = Gson().toJson(database)
        context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
        todoTasks.remove(todoTasks.first { it.uid == uid })
    }

    fun updateTodoTask(newTodoTask: TodoTask){
        var file = File(context.filesDir, databaseFile);
        val bufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val database = Gson().fromJson(inputString, Database::class.java)
        val temp1 = database.todoTasks.first { it.uid == newTodoTask.uid }
        temp1.name = newTodoTask.name
        temp1.done = newTodoTask.done
        val fileContents = Gson().toJson(database)
        context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
        val temp2 = todoTasks.first { it.uid == newTodoTask.uid}
        temp2.name = newTodoTask.name
        temp2.done = newTodoTask.done
        val tempList = todoTasks.toList()
        todoTasks.clear()
        todoTasks.addAll(tempList)
    }

    fun loadTodoTasks() {
        try {
            var file = File(context.filesDir, databaseFile);
            if (!file.exists()) {
                val fileContents = Gson().toJson(Database(mutableListOf<TodoTask>()))
                context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
                    it.write(fileContents.toByteArray())
                }
                file = File(context.filesDir, databaseFile);
            }
            val bufferedReader = file.bufferedReader()
            val inputString = bufferedReader.use { it.readText() }
            println(inputString)
            val database = Gson().fromJson(inputString, Database::class.java)
            todoTasks.addAll(database.todoTasks)
        }
        catch (e : Exception){
            val fileContents = Gson().toJson(Database(mutableListOf<TodoTask>()))
            context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
                it.write(fileContents.toByteArray())
            }
            throw e
        }
    }

    fun addTodoTask(todoTask: TodoTask){
        var file = File(context.filesDir, databaseFile);
        val bufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val database = Gson().fromJson(inputString, Database::class.java)
        database.todoTasks.add(todoTask)
        val fileContents = Gson().toJson(database)
        context.openFileOutput(databaseFile, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }
        todoTasks.add(todoTask)
    }
}