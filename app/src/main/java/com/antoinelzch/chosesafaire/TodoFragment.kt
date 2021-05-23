package com.antoinelzch.chosesafaire

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoinelzch.chosesafaire.data.models.Todo
import com.antoinelzch.chosesafaire.presentation.adapters.TodoAdapter
import kotlinx.android.synthetic.main.fragment_tasks.*
import com.antoinelzch.chosesafaire.presentation.viewmodel.TodoViewModel as TodoViewModel

class TodoFragment: Fragment() {

    private lateinit var viewModel: TodoViewModel
    private var todoList: ArrayList<Todo> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            viewModel = TodoViewModel(it)
        }

        val layoutManager = LinearLayoutManager(this.requireContext())
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        todos_rv.layoutManager = layoutManager
        todos_rv.adapter = TodoAdapter(todoList, {id, isChecked -> onCheckTask(id, isChecked)}, {id -> onDeleteTask(id)})

        todo_new_task_button.setOnClickListener {
            val newTodoName: String = todo_new_input.text.toString()
            if(newTodoName.isNotBlank()) {
                val newTodo = Todo(title = newTodoName, isChecked = false)
                addNewTask(newTodo)

                todo_new_input.text = null
            }
        }

        getTodoList()
    }


    private fun getTodoList() {
        viewModel.getTodos {todoList -> refreshData(todoList) }
    }

    private fun addNewTask(todo: Todo) {
        todoList.add(todo)
        viewModel.insertTodo(todo) {newTodo ->
            todos_rv.adapter?.notifyDataSetChanged()
        }
    }


    private fun refreshData(todoList: List<Todo>?) {
        todoList?.let {
            this.todoList.clear()
            this.todoList.addAll(it)
        }
        todos_rv.adapter?.notifyDataSetChanged()
    }

    private fun onCheckTask(id: Int, isChecked: Boolean) {
        todoList.firstOrNull {it.id == id}?.isChecked = isChecked
    }

    private fun onDeleteTask(id: Int) {
        todoList.firstOrNull {it.id == id}?.let {
            viewModel.deleteTodo(it)
            todoList.remove(it)
            todos_rv.adapter?.notifyDataSetChanged()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.insertTodos(todoList)
    }
}
