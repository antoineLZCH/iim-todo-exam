package com.antoinelzch.chosesafaire

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.setOnClickListener {
            val email: String = register_username.text.toString()
            val password: String = register_password.text.toString()
            val lastname: String = register_lastname.text.toString()
            val firstname: String = register_firstname.text.toString()

            if(isValidate(email, password, lastname, firstname)) {
                setData(firstname, lastname)
                // TODO: Navigation to todo list
                Log.d("tag", "Navigation")
            }
        }
    }


    private fun isValidate(
        email: String,
        password: String,
        lastname: String,
        firstname: String) :Boolean {
        var isValid = true


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid = false
            Toast.makeText(activity, "Email non valide", Toast.LENGTH_SHORT).show()
        }

        if (password.isEmpty() || password.length > 4){
            isValid = false
            Toast.makeText(activity, "Mot de passe non valide, minimum 4 charactères", Toast.LENGTH_SHORT).show()
        }

        if (firstname.isEmpty()){
            isValid = false
            Toast.makeText(activity, "Nom non valide", Toast.LENGTH_SHORT).show()
        }

        if (lastname.isEmpty()){
            isValid = false
            Toast.makeText(activity, "Nom non valide", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }

    private fun setData(firstname: String, lastname: String) {
        val spe: SharedPreferences? = activity?.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        spe?.edit()?.putString("USER_FIRSTNAME", firstname)?.apply()
        spe?.edit()?.putString("USER_LASTNAME", lastname)?.apply()
    }
}