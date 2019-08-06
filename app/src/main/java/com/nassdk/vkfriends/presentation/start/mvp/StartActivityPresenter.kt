package com.nassdk.vkfriends.presentation.start.mvp

import android.content.Intent
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback

@InjectViewState
class StartActivityPresenter : MvpPresenter<StartActivityView>() {

    val PRESENTER = "TOKEN_TUT"

    fun loginVk(requestCode: Int, resultCode: Int, data: Intent?) {
        if (!VK.onActivityResult(requestCode, resultCode, data, object : VKAuthCallback {
                override fun onLogin(vkAccessToken: VKAccessToken) {
                    viewState.openFriends(vkAccessToken.accessToken)
                }

                override fun onLoginFailed(i: Int) {
                    viewState.showLoginFail(i)
                }
            }))
        ;
    }
}