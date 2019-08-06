package com.nassdk.vkfriends.data

import com.nassdk.vkfriends.model.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VkApi {

    @GET("friends.get")
    fun getFriends(
        @Query("fields") fields: String,
        @Query("access_token") access_token: String,
        @Query("v") version: String
    ): Call<MainResponse>

    
}