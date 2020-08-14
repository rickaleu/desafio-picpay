package com.picpay.desafio.android.users.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.model.User
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(user: User?) {
        itemView.name.text = user?.name ?: "Nao Informado"
        itemView.username.text = user?.username ?: "Nao Informado"
        itemView.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(user?.img)
            .error(R.drawable.ic_round_account_circle)
            .into(itemView.picture, object : Callback {
                override fun onSuccess() {
                    itemView.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    itemView.progressBar.visibility = View.GONE
                }
            })
    }
}