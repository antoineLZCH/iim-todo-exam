package com.antoinelzch.chosesafaire.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.antoinelzch.chosesafaire.data.db.AppDatabase
import com.antoinelzch.chosesafaire.data.models.Todo
import kotlinx.coroutines.*

class TodoViewModel(context: Context): ViewModel() {

    val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "ChosesAFaire"
    ).build()

    fun test(): List<Todo> {
        val todoDao = database.todoDao()
        return todoDao.getTodos()
    }

    fun insertTodos(list: List<Todo>) {
      GlobalScope.launch {
          val todoDao = database.todoDao()
          todoDao.addAllTodos(list)
      }
    }

    fun insertTodo(todo: Todo) {
        CoroutineScope(Dispatchers.IO).launch {
            val todoDao = database.todoDao()
            todoDao.addTodo(todo)
        }
    }

    fun getTodos(): List<Todo> {
        val todoDao = database.todoDao()
        return todoDao.getTodos()
    }

}