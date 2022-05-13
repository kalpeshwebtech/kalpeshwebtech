package com.webecom.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.model.FilterChildModel
import com.webecom.model.NavigationItemModel
import com.webecom.model.ReviewsModel
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.navigation_title
import kotlinx.android.synthetic.main.row_reviews.view.*

class ReviewsAdapter(private var items: ArrayList<ReviewsModel>,private var isDetail:Boolean) :
    RecyclerView.Adapter<ReviewsAdapter.NavigationItemViewHolder>(), Filterable {

    private lateinit var context: Context
    var itemList = ArrayList<ReviewsModel>()
    init {
        this.itemList = items
    }
    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_reviews, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        if (isDetail){
            if (itemList.size>3){
                return 3;
            }else{
                return itemList.count()
            }
        }else {
            return itemList.count()
        }
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemList = items
                } else {
                    val resultList = ArrayList<ReviewsModel>()
                    for (row in items) {
                        if (row.review.trim().lowercase().contains(charSearch.lowercase().trim())
                        ) {
                            resultList.add(row)
                        }
                    }
                    itemList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = itemList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemList = results?.values as ArrayList<ReviewsModel>
                notifyDataSetChanged()
            }
        }
    }
    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {

        holder.itemView.tvRating.text = itemList[position].review
        holder.itemView.title.text = itemList[position].title
        holder.itemView.tvDesc.text = itemList[position].desc
        holder.itemView.tvName.text = itemList[position].name
        holder.itemView.tvLike.text = itemList[position].like
        holder.itemView.tvDisLike.text = itemList[position].dislike
        itemList[position].images.let {
            val imageAdapter=ReviewImageAdapter(it)
            holder.itemView.recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            holder.itemView.recyclerView.setHasFixedSize(true)
            holder.itemView.recyclerView.adapter = imageAdapter
        }
    }

}
