package com.antoinelzch.chosesafaire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.antoinelzch.chosesafaire.data.utils.SPUtils

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SPUtils.init(this)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        navController = navHost.navController

        goToTodoFragment()
    }

    fun goTo(id: Int) {
        navController.navigate(id)
    }

    private fun goToTodoFragment() {
        if(!(SPUtils.getFirstName().isNullOrBlank() && SPUtils.getLastName().isNullOrBlank())) {
            navController.navigate(R.id.action_loginFragment_to_tasksFragment)
        }
    }

}