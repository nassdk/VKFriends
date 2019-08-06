package com.nassdk.vkfriends.presentation.friends.mvp

import com.arellomobile.mvp.MvpView
import com.nassdk.vkfriends.model.Friend

interface FriendsActivityView: MvpView {
    fun showProgress()
    fun hideProgress()
    fun setUpAdapter(friends: List<Friend>)
    fun showError(t: Throwable)

}