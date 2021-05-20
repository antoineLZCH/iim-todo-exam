package com.antoinelzch.chosesafaire

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.antoinelzch.chosesafaire.data.db.AppDatabase
import com.antoinelzch.chosesafaire.data.models.Todo
import com.antoinelzch.chosesafaire.presentation.viewmodel.TodoViewModel

class TasksFragment: Fragment() {

    private lateinit var viewModel: TodoViewModel

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

        getTodoList()
    }

    private fun getTodoList() {
    }

    private fun createNewTodo(response: Todo) {
        viewModel.insertTodo(response)
    }
}
