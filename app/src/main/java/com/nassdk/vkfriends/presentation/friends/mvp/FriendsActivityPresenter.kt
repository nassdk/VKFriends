package com.nassdk.vkfriends.presentation.friends.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.nassdk.vkfriends.model.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@InjectViewState
class FriendsActivityPresenter : MvpPresenter<FriendsActivityView>() {

    fun friendsCall (call: Call<MainResponse>) {
        viewState.showProgress()
        call.enqueue(object : Callback<MainResponse> {
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                val listOfFriends = response.body()?.response?.friends
                viewState.setUpAdapter(listOfFriends!!)
                viewState.hideProgress()
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                viewState.showError(t)
                viewState.hideProgress()
            }
        })
    }
}