package com.antoinelzch.chosesafaire

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.antoinelzch.chosesafaire.data.network.ServiceBuilder
import com.antoinelzch.chosesafaire.data.network.UserService
import com.antoinelzch.chosesafaire.data.utils.SPUtils
import com.antoinelzch.chosesafaire.data.utils.ValidationUtils
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        main_button_sign_in.setOnClickListener {
            val email: String = main_email.text.toString()
            val password: String = main_password.text.toString()

            Log.d("tag", "${main_password.text}, ${main_email.text}")
            if (isDataValid(email, password)){
                callApi(email, password)
            }
        }

        main_button_register.setOnClickListener {
            (activity as MainActivity).goTo(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun isDataValid(email: String, password : String) :Boolean {
        var isValid = true

        if (!ValidationUtils.isEmailValid(email)){
            isValid = false
            Toast.makeText(activity, "Email non valide", Toast.LENGTH_SHORT).show()
        }

        if (!ValidationUtils.isPasswordValid(password)){
            isValid = false
            Toast.makeText(activity, "Mot de passe non valide, minimum 4 charactères", Toast.LENGTH_SHORT).show()

        }

        return isValid
    }

    private fun callApi(email: String, password : String) {
        val request = ServiceBuilder.buildService(UserService::class.java)
        val call = request.login()

        call.enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                response.body()?.let {
                    SPUtils.setBoth(email, password)
                    (activity as MainActivity).goTo(R.id.action_loginFragment_to_tasksFragment)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun isLogged(): Boolean {
        return !SPUtils.getFirstName().isNullOrBlank() && !SPUtils.getLastName().isNullOrBlank()
    }
}