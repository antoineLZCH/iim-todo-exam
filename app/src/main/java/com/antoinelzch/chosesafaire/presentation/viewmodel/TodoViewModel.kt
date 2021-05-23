package com.antoinelzch.chosesafaire.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.antoinelzch.chosesafaire.data.db.AppDatabase
import com.antoinelzch.chosesafaire.data.models.Todo
import kotlinx.coroutines.*

class TodoViewModel(context: Context): ViewModel() {

    private val database = AppDatabase.get(context)

    fun insertTodos(todoList: List<Todo>) {
      GlobalScope.launch {
          val todoDao = database?.todoDao()
          todoDao?.addAllTodos(todoList)
      }
    }

    fun insertTodo(todo: Todo, listener: (Todo) -> Unit) {
        GlobalScope.launch {
            val todoDao = database?.todoDao()
            todoDao?.let {
                todoDao.addTodo(todo)?.let {
                    todo.id = it.toInt()
                    withContext(Dispatchers.Main) { listener(todo)}
                }
            }
        }
    }

    fun deleteTodo(todo: Todo) {
        GlobalScope.launch {
            val todoDao = database?.todoDao()
            todoDao?.deleteTodo(todo)
        }
    }

    fun getTodos(listener: (List<Todo>?) -> Unit) {
        GlobalScope.launch {
            val todoDao = database?.todoDao()
            val todoList = todoDao?.getTodos()
            withContext(Dispatchers.Main) {
                listener(todoList)
            }
        }
    }

}