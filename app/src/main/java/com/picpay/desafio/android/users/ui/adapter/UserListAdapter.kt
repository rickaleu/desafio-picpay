package com.picpay.desafio.android.users.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.User
import com.picpay.desafio.android.users.ui.viewholder.UserListItemViewHolder

class UserListAdapter : RecyclerView.Adapter<UserListItemViewHolder>() {

    private var mList: List<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)

        return UserListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    fun list(listAllUsers: List<User>) {
        mList = listAllUsers
        notifyDataSetChanged()
    }
}