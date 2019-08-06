package com.nassdk.vkfriends.model

import com.google.gson.annotations.SerializedName

class LastSeen {

    @SerializedName("time")
    var time: Int? = null

    @SerializedName("platform")
    var platform: Int? = null
}