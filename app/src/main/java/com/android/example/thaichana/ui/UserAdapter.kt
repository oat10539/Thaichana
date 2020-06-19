package com.android.example.thaichana.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.thaichana.R
import com.android.example.thaichana.database.User
import kotlinx.android.synthetic.main.fragment_checkin.view.*
import kotlinx.android.synthetic.main.list_layout.view.*


//use in historyFragment
class UserAdapter(private val user:List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_layout,parent,false)
        )
    }

    override fun getItemCount()=user.size


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.placehistory.text = user[position].place
        holder.view.datetime.text = user[position].date
    }



class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}
