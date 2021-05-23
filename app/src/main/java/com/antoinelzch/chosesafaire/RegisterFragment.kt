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
import com.antoinelzch.chosesafaire.data.utils.SPUtils
import com.antoinelzch.chosesafaire.data.utils.ValidationUtils
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

            if(isDataValid(email, password, lastname, firstname)) {
                SPUtils.setBoth(firstname, lastname)
                Log.d("tag", "USER IS REGISTERED - NAVIGATION TO TODO FRAGMENT")
                (activity as MainActivity).goTo(R.id.action_registerFragment_to_tasksFragment)
            }
        }
    }

    private fun isDataValid(
        email: String,
        password: String,
        lastname: String,
        firstname: String) :Boolean {
        var isValid = true


        if (!ValidationUtils.isEmailValid(email)){
            isValid = false
            Toast.makeText(activity, "Email non valide", Toast.LENGTH_SHORT).show()
        }

        if (!ValidationUtils.isPasswordValid(password)){
            isValid = false
            Toast.makeText(activity, "Mot de passe non valide, minimum 4 charactères", Toast.LENGTH_SHORT).show()
        }

        if (!ValidationUtils.isFirstNameNotEmptyOrBlank(firstname)){
            isValid = false
            Toast.makeText(activity, "Le champ Prénom ne doit pas être vide", Toast.LENGTH_SHORT).show()
        }

        if (!ValidationUtils.isLastNameNotEmptyOrBlank(lastname)){
            isValid = false
            Toast.makeText(activity, "Le champ Nom ne doit pas être vide", Toast.LENGTH_SHORT).show()
        }

        return isValid
    }
}