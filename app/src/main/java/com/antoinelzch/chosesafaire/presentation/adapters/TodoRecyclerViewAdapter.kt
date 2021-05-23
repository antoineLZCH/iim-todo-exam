package com.antoinelzch.chosesafaire.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoinelzch.chosesafaire.R
import com.antoinelzch.chosesafaire.data.models.Todo
import kotlinx.android.synthetic.main.task.view.*

class TodoAdapter(
    private val todoList: ArrayList<Todo>,
    private val onButtonCheckListener: (Int, Boolean) -> Unit,
    private val onButtonDeleteListener: (Int) -> Unit
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.task, parent, false)
        return TodoViewHolder(viewHolder)
    }

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        return holder.bind(todoList[position], onButtonCheckListener, onButtonDeleteListener)
    }

    inner class TodoViewHolder(private val todoView: View): RecyclerView.ViewHolder(todoView) {
        fun bind(todo: Todo, onButtonCheckListener: (Int, Boolean) -> Unit, onButtonDeleteListener: (Int) -> Unit) {
            todoView.task_title.text = todo.title
            todoView.check_task_button.isChecked = todo.isChecked

            todoView.setOnClickListener {
                todoView.check_task_button.performClick()
                todoView.check_task_button.isPressed = true
                todoView.check_task_button.invalidate()
                todoView.check_task_button.isPressed = false
                todoView.check_task_button.invalidate()
            }

            todoView.check_task_button.setOnCheckedChangeListener { _, isChecked ->
                onButtonCheckListener(todo.id, isChecked)
            }

            todoView.delete_task_button.setOnClickListener {
                onButtonDeleteListener(todo.id)
            }
        }
    }
}