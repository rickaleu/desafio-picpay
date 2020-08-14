package com.picpay.desafio.android.data.local

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val mPreferences: SharedPreferences =
        context.getSharedPreferences("userShared", Context.MODE_PRIVATE)

    fun storedUser(key: String, value: Boolean) {
        mPreferences.edit().putString(key, value.toString()).apply()
    }

    fun getUser(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

}