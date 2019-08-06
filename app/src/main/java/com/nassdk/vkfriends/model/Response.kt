package com.nassdk.vkfriends.model


import com.google.gson.annotations.SerializedName

class Response {

    @SerializedName("count")
    var count: Int? = null

    @SerializedName("items")
    var friends: List<Friend>? = null
        private set

    fun setItems(friends: List<Friend>) {
        this.friends = friends
    }

}