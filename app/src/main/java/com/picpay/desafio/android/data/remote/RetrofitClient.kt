package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.data.Paths
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor(){

    companion object {
        private lateinit var retrofit : Retrofit

        private fun getRetrofitInstance() : Retrofit {
            var httpClient = OkHttpClient.Builder()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Paths.USERS_LIST_API)
                    .client(httpClient.build())
                    .build()
            }

            return retrofit
        }

        fun<T> createService(serviceClass: Class<T>) : T {
            return getRetrofitInstance().create(serviceClass)
        }
    }

}