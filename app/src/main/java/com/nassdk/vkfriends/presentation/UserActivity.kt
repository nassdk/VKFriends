package com.nassdk.vkfriends.presentation

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nassdk.vkfriends.R

class UserActivity : MvpAppCompatActivity(), UserActivityView {

    @InjectPresenter
    lateinit var presenter: UserActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
    }
}
