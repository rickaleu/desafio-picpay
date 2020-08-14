package com.picpay.desafio.android.users.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.users.ui.adapter.UserListAdapter
import com.picpay.desafio.android.users.viewmodel.UsersViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mUserViewModel: UsersViewModel

    private lateinit var recyclerUserList: RecyclerView
    private var progressBarMain: ProgressBar? = null
    private lateinit var mAdapter: UserListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mUserViewModel = ViewModelProviders.of(this).get(UsersViewModel::class.java)

        bindComponents()
        callUsersApi()
        observe()

        mUserViewModel.listUser()
    }

    private fun bindComponents() {
        recyclerUserList = findViewById(R.id.recyclerView)
        progressBarMain = findViewById(R.id.progressBar)

        showLoading(true)

        loadList()
    }

    private fun showLoading(status: Boolean) {
        if(status) {
            progressBarMain?.visibility = VISIBLE
        } else {
            progressBarMain?.visibility = GONE
        }
    }

    private fun loadList() {
        mAdapter = UserListAdapter()
        recyclerUserList.layoutManager = LinearLayoutManager(this)
        recyclerUserList.adapter = mAdapter
    }

    private fun callUsersApi() {
        progressBarMain?.visibility = VISIBLE
        mUserViewModel.getUsers()
    }

    private fun observe() {
        mUserViewModel.localUser.observe(this, Observer {
            mAdapter.list(it)
        })

        mUserViewModel.user.observe(this, Observer {
            if (it != null) {
                showLoading(false)
                mAdapter.list(it)
            }
        })

        mUserViewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        mUserViewModel.getUsers()
    }
}
