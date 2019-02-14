package nikhilbhutani.github.io.dextra.ui.dexes

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.child_rv_users.view.*
import nikhilbhutani.github.io.dextra.R
import nikhilbhutani.github.io.dextra.data.remote.models.UserModel.DexUserResponse
import sportso.sportso.io.sportso.utils.GlideApp

class DexUsersRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<DexUsersRecyclerViewAdapter.UserViewHolder>() {


    var users: List<DexUserResponse.User> = ArrayList()

    fun addUsers(users: List<DexUserResponse.User>) {
        this.users = users
        this.notifyDataSetChanged()
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {

        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.child_rv_users, viewGroup, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(userViewHolder: UserViewHolder, p1: Int) {

        val user = users[p1]

        GlideApp.with(context).load(user.imageUrl).into(userViewHolder.imageViewUser)
        userViewHolder.firstName.text = user.firstName
        userViewHolder.lastName.text = user.lastName
        userViewHolder.clapCount.text = user.totalProjectClaps.toString()

    }


    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewUser: ImageView = itemView.user_items
        val firstName: TextView = itemView.user_name
        val lastName: TextView = itemView.user_last_name
        val clapCount: TextView = itemView.clapCount

    }

}