package com.picpay.desafio.android.users.repository

import android.content.Context
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.local.UserDatabase
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.data.remote.PicPayService
import com.picpay.desafio.android.data.remote.RetrofitClient
import com.picpay.desafio.android.utils.APIListener
import com.picpay.desafio.android.utils.ConnectionUtil.Companion.isConnectionAvailable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsersListRepositoryImpl(private val context: Context) : UserListRepository {

    private val mRemote = RetrofitClient.createService(PicPayService::class.java)
    private val mUserDatabase = UserDatabase.getDatabase(context).userDAO()

    override fun getUser(listener: APIListener<List<User>>) {

        if(!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.error_internet_connection))
            return
        } else {
            mRemote.getUser().enqueue(object : Callback<List<User>> {
                override fun onFailure(call: Call<List<User>>, t: Throwable) {
                    listener.onFailure(t.message.toString())
                }

                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    response.body()?.let { listener.onSuccess(it) }
                    response.body()?.let { mUserDatabase.saveUser(it) }
                }
            })
        }
    }

    override fun getUsersDatabase() = mUserDatabase.listUser()
}