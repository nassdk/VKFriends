package com.nassdk.vkfriends.model

import com.google.gson.annotations.SerializedName

class Friend {

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("first_name")
    var firstName: String? = null

    @SerializedName("last_name")
    var lastName: String? = null

    @SerializedName("online")
    var online: Int? = null

    @SerializedName("photo_100")
    var imageUri: String? = null

    @SerializedName("city")
    var city: City? = null

    @SerializedName("photo_200_orig")
    var photo200Orig: String? = null

    @SerializedName("last_seen")
    var lastSeen: LastSeen? = null
}