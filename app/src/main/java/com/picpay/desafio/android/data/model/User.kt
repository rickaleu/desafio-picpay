package com.picpay.desafio.android.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
class User {

    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String = ""

    @SerializedName("username")
    @ColumnInfo(name = "username")
    var username: String = ""

    @SerializedName("img")
    @ColumnInfo(name = "img")
    var img: String = ""
}