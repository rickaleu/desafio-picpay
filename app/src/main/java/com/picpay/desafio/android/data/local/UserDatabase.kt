package com.picpay.desafio.android.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDAO() : UserDAO

    companion object {
        private lateinit var INSTANCE: UserDatabase

        fun getDatabase(context: Context) : UserDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(UserDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, UserDatabase::class.java, "userDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}