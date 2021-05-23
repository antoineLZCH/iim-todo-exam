package com.antoinelzch.chosesafaire.data.network

import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @GET("/login")
    fun login(): Call<Void>

}