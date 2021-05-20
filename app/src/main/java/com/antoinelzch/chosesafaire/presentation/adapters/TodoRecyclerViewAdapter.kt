package com.antoinelzch.chosesafaire.presentation.adapters

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antoinelzch.chosesafaire.R
import com.antoinelzch.chosesafaire.data.models.Todo
import kotlinx.android.synthetic.main.task.view.*

class TodoRecyclerViewAdapter(
    private var todos: MutableList<Todo>,
    val context: Context?
): RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>() {

    class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.task,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos() {
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(todoTitle: TextView, isChecked: Boolean) {
        if(isChecked) todoTitle.paintFlags = todoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        else todoTitle.paintFlags = todoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currTodo = todos[position]
        holder.itemView.apply {
            task_text.text = currTodo.title
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }

}