package nikhilbhutani.github.io.dextra.ui.main

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.child_rv_banner.view.*
import kotlinx.android.synthetic.main.child_rv_dex.view.*
import kotlinx.android.synthetic.main.child_rv_requirements.view.*
import kotlinx.android.synthetic.main.child_rv_users.view.*
import nikhilbhutani.github.io.dextra.R
import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse
import nikhilbhutani.github.io.dextra.ui.listeners.RecyclerViewItemClickListener
import sportso.sportso.io.sportso.utils.GlideApp

class ChildRecyclerviewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mEnititesList: List<ExploreResponse.Entities> = ArrayList()

    private var recyclerViewItemClickListener: RecyclerViewItemClickListener = context as RecyclerViewItemClickListener

    private lateinit var parentHolder: HomeParentRecyclerviewAdapter.MyViewHolder
    private lateinit var sectionType: String

    val VIEW_TYPE_BANNER = 0
    val VIEW_TYPE_REQUIREMENT = 1
    val VIEW_TYPE_DEX = 2
    val VIEW_TYPE_USER = 3

    fun setEntitiesList(
        entities: List<ExploreResponse.Entities>,
        sectionType: String,
        holder: HomeParentRecyclerviewAdapter.MyViewHolder
    ) {

        this.mEnititesList = entities
        this.sectionType = sectionType
        this.parentHolder = holder
        this.notifyDataSetChanged()
    }


    override fun getItemViewType(position: Int): Int {

        when (this.sectionType) {
            "requirement" -> {
                return VIEW_TYPE_REQUIREMENT
            }
            "banner" -> {
                return VIEW_TYPE_BANNER
            }
            "dex" -> {
                return VIEW_TYPE_DEX
            }
            "user" -> {
                return VIEW_TYPE_USER
            }
        }

        return 0
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var holder: RecyclerView.ViewHolder

        when (viewType) {
            VIEW_TYPE_BANNER -> {
                val itemView =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.child_rv_banner, viewGroup, false)
                holder = BannerViewHolder(itemView)

            }
            VIEW_TYPE_REQUIREMENT -> {
                val itemView =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.child_rv_requirements, viewGroup, false)
                holder = ReqViewHolder(itemView)

            }
            VIEW_TYPE_DEX -> {
                val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.child_rv_dex, viewGroup, false)
                holder = DexViewHolder(itemView)
            }
            VIEW_TYPE_USER -> {
                val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.child_rv_users, viewGroup, false)
                holder = UserViewHolder(itemView)

            }
        }
        return holder
    }

    override fun getItemCount(): Int {
        return this.mEnititesList.size
    }

    override fun onBindViewHolder(childHolder: RecyclerView.ViewHolder, position: Int) {

        val childEntity = mEnititesList.get(position)

        when (childHolder.getItemViewType()) {

            VIEW_TYPE_BANNER -> {
                val bannerViewHolder = childHolder as BannerViewHolder
                GlideApp.with(context).load(childEntity.imageUrl).into(bannerViewHolder.imageViewBanner)
            }

            VIEW_TYPE_REQUIREMENT -> {
                val reqViewHolder = childHolder as ReqViewHolder
                GlideApp.with(context).load(childEntity.imageUrl).into(reqViewHolder.imageViewReq)
            }

            VIEW_TYPE_DEX -> {
                val dexViewHolder = childHolder as DexViewHolder
                GlideApp.with(context).load(childEntity.imageUrl).into(dexViewHolder.imageViewDex)
                dexViewHolder.userterms.text = childEntity.userTerm
            }

            VIEW_TYPE_USER -> {
                val userViewHolder = childHolder as UserViewHolder
                GlideApp.with(context).load(childEntity.imageUrl).into(userViewHolder.imageViewUser)
                userViewHolder.firstName.text = childEntity.firstName
                userViewHolder.lastName.text = childEntity.lastName
                userViewHolder.clapCount.text = childEntity.totalProjectClaps.toString()

            }
        }
    }


    inner class ReqViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imageViewReq: ImageView = itemView.requirement_items
    }

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        val imageViewBanner: ImageView = itemView.banner_item

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            recyclerViewItemClickListener.onBannerClick(parentHolder.adapterPosition, adapterPosition)
        }
    }

    inner class DexViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val imageViewDex: ImageView = itemView.dex_items
        val userterms: TextView = itemView.userterms

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            recyclerViewItemClickListener.onDexClick(parentHolder.adapterPosition, adapterPosition)
        }

    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageViewUser: ImageView = itemView.user_items
        val firstName: TextView = itemView.user_name
        val lastName: TextView = itemView.user_last_name
        val clapCount: TextView = itemView.clapCount

    }

}