package com.picpay.desafio.android.utils

interface APIListener<T> {

    fun onSuccess(list: T)
    fun onFailure(message: String)
}