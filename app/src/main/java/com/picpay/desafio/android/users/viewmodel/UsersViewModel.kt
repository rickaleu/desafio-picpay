package com.picpay.desafio.android.users.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.data.local.SecurityPreferences
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.users.repository.UsersListRepositoryImpl
import com.picpay.desafio.android.utils.APIListener
import com.picpay.desafio.android.utils.Constants

class UsersViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository = UsersListRepositoryImpl(application)
    private val mSharedPreferences = SecurityPreferences(application)

    private val mUser = MutableLiveData<List<User>>()
    var user: LiveData<List<User>> = mUser

    private val mErrorMessage = MutableLiveData<String>()
    var errorMessage: LiveData<String> = mErrorMessage

    private val mLocalUser  =  MutableLiveData<List<User>>()
    var localUser: LiveData<List<User>> = mLocalUser

    fun getUsers() {
        val userListStatus = mSharedPreferences.getUser(Constants.SHARED.LIST)
        val status = (userListStatus != "")

        if (!status) {
            mRepository.getUser(object : APIListener<List<User>> {
                override fun onSuccess(list: List<User>) {
                    mSharedPreferences.storedUser(Constants.SHARED.LIST, true)
                    mUser.value = list
                }

                override fun onFailure(message: String) {
                    mErrorMessage.value = message
                }
            })
        } else {
            mRepository.getUsersDatabase()
        }
    }

    fun listUser(){
        mLocalUser.value = mRepository.getUsersDatabase()
    }
}