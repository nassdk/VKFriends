package com.nassdk.vkfriends.presentation.start.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nassdk.vkfriends.R
import com.nassdk.vkfriends.presentation.friends.ui.FriendsActivity
import com.nassdk.vkfriends.presentation.start.mvp.StartActivityPresenter
import com.nassdk.vkfriends.presentation.start.mvp.StartActivityView
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.activity_main.*

class StartActivity : MvpAppCompatActivity(), StartActivityView {

    @InjectPresenter
    lateinit var presenter: StartActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        butAuth.setOnClickListener {
            VK.login(this@StartActivity, setOf(VKScope.FRIENDS))
        }
    }

    override fun showInternetError() {
        Toast.makeText(applicationContext, "Please, check your internet connection and try later", Toast.LENGTH_SHORT)
            .show()
    }

    override fun openFriends(token: String) {
        val intent = Intent(applicationContext, FriendsActivity::class.java)
        intent.putExtra("token",token)
        startActivity(intent)
        finish()
    }

    override fun showLoginFail(i: Int) {
        Toast.makeText(applicationContext, i.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        presenter.loginVk(requestCode, resultCode, data)
    }
}
