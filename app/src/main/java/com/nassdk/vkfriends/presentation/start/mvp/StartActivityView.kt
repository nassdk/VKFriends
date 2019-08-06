package com.nassdk.vkfriends.presentation.start.mvp

import com.arellomobile.mvp.MvpView

interface StartActivityView : MvpView {

    fun showInternetError()
    fun showLoginFail(i: Int)
    fun openFriends(token: String)
}