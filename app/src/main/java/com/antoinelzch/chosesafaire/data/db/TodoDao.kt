package com.antoinelzch.chosesafaire.data.db

import androidx.room.*
import com.antoinelzch.chosesafaire.data.models.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM ChosesAFaire")
    fun getTodos() : List<Todo>

    @Insert
    fun addAllTodos(todos: List<Todo>)

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)
}