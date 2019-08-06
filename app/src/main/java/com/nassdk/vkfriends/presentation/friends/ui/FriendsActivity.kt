package com.nassdk.vkfriends.presentation.friends.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.nassdk.vkfriends.R
import com.nassdk.vkfriends.data.VkApi
import com.nassdk.vkfriends.model.Friend
import com.nassdk.vkfriends.presentation.friends.mvp.FriendsActivityPresenter
import com.nassdk.vkfriends.presentation.friends.mvp.FriendsActivityView
import com.nassdk.vkfriends.presentation.friends.adapter.FriendsAdapter
import kotlinx.android.synthetic.main.activity_friends.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FriendsActivity : MvpAppCompatActivity(), FriendsActivityView {

    val BASE_URL = "https://api.vk.com/method/"

    private val TAG = FriendsActivity::class.java.simpleName

    private lateinit var recView_Friends: RecyclerView
    private lateinit var api: VkApi

    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    private val FIELDS = "first_name,photo_100,online"
    private val VERSION = "5.101"

    @InjectPresenter
    lateinit var presenter: FriendsActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)
        sharedPref = getSharedPreferences("token", Context.MODE_PRIVATE)
        createVkApi()
    }

    private fun createVkApi() {
        recView_Friends = findViewById(R.id.recView_Friends)

        intent = intent
        val token = intent!!.getStringExtra("token")

        editor = sharedPref.edit()
        editor.putString("token", token)
        editor.apply()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(VkApi::class.java)
        Log.e(TAG, token.toString())

        val callResponse = api.getFriends(FIELDS, token, VERSION)
        presenter.friendsCall(callResponse)
    }

    override fun showProgress() {
        spinner.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        spinner.visibility = View.GONE
    }

    override fun setUpAdapter(listOfFriends: List<Friend>) {
        val adapter = FriendsAdapter(listOfFriends)
        recView_Friends.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        recView_Friends.addItemDecoration(
            DividerItemDecoration(
                recView_Friends.context,
                DividerItemDecoration.VERTICAL
            )
        )
        recView_Friends.adapter = adapter
    }

    override fun showError(t: Throwable) {
        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
    }
}
