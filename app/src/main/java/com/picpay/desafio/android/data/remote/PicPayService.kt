package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.model.User
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUser(): Call<List<User>>
}