package com.nassdk.vkfriends.presentation.friends.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.nassdk.vkfriends.R
import com.nassdk.vkfriends.model.Friend

import de.hdodenhof.circleimageview.CircleImageView

class FriendsAdapter(private val friendModelList: List<Friend>) : RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(friendModelList[position])
    }

    override fun getItemCount(): Int {
        return friendModelList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvFirstName: TextView
        var tvLastName: TextView
        var civ_UserImage: CircleImageView
        var civ_Online: CircleImageView

        init {

            tvFirstName = itemView.findViewById(R.id.tvFirstName)
            tvLastName = itemView.findViewById(R.id.tvLastName)
            civ_UserImage = itemView.findViewById(R.id.civ_UserImage)
            civ_Online = itemView.findViewById(R.id.civ_Online)
        }

        fun bind(friend: Friend) {
            tvFirstName.text = friend.firstName
            tvLastName.text = friend.lastName
            if (friend.online == 1) {
                civ_Online.visibility = View.VISIBLE
            } else {
                civ_Online.visibility = View.GONE
            }

            Glide
                .with(itemView.context)
                .load(friend.imageUri)
                .placeholder(R.drawable.ic_placeholder)
                .into(civ_UserImage)
        }
    }
}
