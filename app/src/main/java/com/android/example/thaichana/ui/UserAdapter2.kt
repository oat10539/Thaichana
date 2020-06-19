package com.android.example.thaichana.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.example.thaichana.R
import com.android.example.thaichana.database.User
import kotlinx.android.synthetic.main.list_layoutwithbtn.view.*
import java.util.logging.Handler

//use in checkinFragment
class UserAdapter2(private val user: List<User>, var clickListener: checkoutClickListener) :
    RecyclerView.Adapter<UserAdapter2.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_layoutwithbtn, parent, false)
        )
    }

    override fun getItemCount() = user.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.view.placeshow.text = user[position].place
        holder.view.datetimeshow.text = user[position].date
        holder.initialize(user.get(position), clickListener)
    }


    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun initialize(item: User, action: checkoutClickListener) {
            view.checkoutbtn.setOnClickListener {
                action.onItemClick(item, adapterPosition)

            }
        }
    }
}

interface checkoutClickListener {
    fun onItemClick(item: User, position: Int)

}
