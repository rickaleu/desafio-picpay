package com.picpay.desafio.android.users.repository

import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.utils.APIListener

interface UserListRepository {

    fun getUser(listener: APIListener<List<User>>)
    fun getUsersDatabase(): List<User>

}