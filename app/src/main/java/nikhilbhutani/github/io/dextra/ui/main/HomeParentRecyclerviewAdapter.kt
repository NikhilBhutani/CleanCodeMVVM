package nikhilbhutani.github.io.dextra.ui.main

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.rv_horizontal_banner.view.*

import nikhilbhutani.github.io.dextra.R


import nikhilbhutani.github.io.dextra.data.remote.models.ExploreModel.ExploreResponse

class HomeParentRecyclerviewAdapter : RecyclerView.Adapter<HomeParentRecyclerviewAdapter.MyViewHolder>() {

    private var mExploreList: List<ExploreResponse.Explore> = ArrayList()

    private val viewPool = RecyclerView.RecycledViewPool()

    val VIEW_TYPE_BANNER = 0
    val VIEW_TYPE_REQUIREMENT = 1
    val VIEW_TYPE_DEX = 2
    val VIEW_TYPE_USER = 3


    fun setExploreResponseList(exploreList: List<ExploreResponse.Explore>) {
        this.mExploreList = exploreList
    }

    override fun getItemViewType(position: Int): Int {

        when (mExploreList.get(position).sectionType) {
            "requirement" -> return VIEW_TYPE_REQUIREMENT
            "banner" -> return VIEW_TYPE_BANNER
            "dex" -> return VIEW_TYPE_DEX
            "user" -> return VIEW_TYPE_USER
        }

        return VIEW_TYPE_BANNER
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MyViewHolder {
        lateinit var holder: MyViewHolder

        val itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.rv_horizontal_banner, viewGroup, false)
        holder = MyViewHolder(itemView)
        return holder
    }


    override fun getItemCount(): Int {
        return mExploreList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val exploreItem = mExploreList[position]


        if (exploreItem.entities.isNotEmpty()) {
            holder.textView.visibility = View.VISIBLE

            holder.textView.text = exploreItem.title
            val childLayoutManager = LinearLayoutManager(
                holder.recyclerView.context,
                LinearLayout.HORIZONTAL, false
            )
            childLayoutManager.initialPrefetchItemCount = 3
            holder.recyclerView.layoutManager = childLayoutManager

            holder.recyclerView.apply {
                val childRecyclerviewAdapter = ChildRecyclerviewAdapter(context)
                holder.recyclerView.adapter = childRecyclerviewAdapter
                childRecyclerviewAdapter.setEntitiesList(exploreItem.entities, exploreItem.sectionType, holder)
                childRecyclerviewAdapter.notifyDataSetChanged()
                setRecycledViewPool(viewPool)
            }
        } else {
            holder.textView.visibility = View.GONE
        }

    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView: TextView = itemView.explore_title
        val recyclerView: RecyclerView = itemView.rv_child

    }

}