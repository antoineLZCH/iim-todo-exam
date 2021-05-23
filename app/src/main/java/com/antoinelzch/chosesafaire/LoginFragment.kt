package com.antoinelzch.chosesafaire

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antoinelzch.chosesafaire.data.network.ServiceBuilder
import com.antoinelzch.chosesafaire.data.network.UserService
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Response


class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.antoinelzch.chosesafaire.R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isLogged()) {
            // TODO: Navigation to todo list
            Log.d("tag", "Navigation")
        }

        val email: String = main_username.text.toString()
        val password: String = main_password.text.toString()

        Log.d("tag", "${main_password.text}, ${main_username.text}")

        main_button_sign_in.setOnClickListener {
            if (isValidate(email, password)){
                callApi(email, password)
            }
        }
    }

    private fun isValidate(email: String, password : String) :Boolean {
        var isValid = true

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            isValid = false
            Toast.makeText(activity, "Email non valide", Toast.LENGTH_SHORT).show()
        }

        if (password.isEmpty() || password.length > 4){
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

                val firstname: String? = activity?.getSharedPreferences("PREFERENCES", AppCompatActivity.MODE_PRIVATE)
                    ?.getString("USER_FIRSTNAME", "")
                val lastname: String? = activity?.getSharedPreferences("PREFERENCES", AppCompatActivity.MODE_PRIVATE)
                    ?.getString("USER_LASTNAME", "")

                response.body()?.let {
                    setData(firstname, lastname)
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(activity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun setData(firstname: String?, lastname: String?) {
        val spe: SharedPreferences? = activity?.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE)
        spe?.edit()?.putString("USER_FIRSTNAME", firstname)?.apply()
        spe?.edit()?.putString("USER_LASTNAME", lastname)?.apply()
    }

    private fun isLogged(): Boolean {
        return !activity?.getSharedPreferences("PREFERENCES", AppCompatActivity.MODE_PRIVATE)
            ?.getString("USER_FIRSTNAME", "")
            .isNullOrEmpty()
                && !activity?.getSharedPreferences("PREFERENCES", AppCompatActivity.MODE_PRIVATE)
            ?.getString("USER_LASTNAME", "")
            .isNullOrEmpty()
    }


}