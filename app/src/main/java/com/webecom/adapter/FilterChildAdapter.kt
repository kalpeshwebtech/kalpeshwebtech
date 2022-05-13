package com.webecom.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.model.FilterChildModel
import com.webecom.model.FilterModel
import com.webecom.model.NavigationItemModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.row_filter.view.*
import kotlinx.android.synthetic.main.row_filter.view.lyMain
import kotlinx.android.synthetic.main.row_filter.view.title
import kotlinx.android.synthetic.main.row_filter_child.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.navigation_icon
import java.util.*
import kotlin.collections.ArrayList

class FilterChildAdapter(private var items: ArrayList<FilterChildModel>) :
    RecyclerView.Adapter<FilterChildAdapter.NavigationItemViewHolder>(), Filterable {

    private lateinit var context: Context
    private var currentPos: Int = 0
    var itemList = ArrayList<FilterChildModel>()

    init {
        this.itemList = items
    }

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem =
            LayoutInflater.from(parent.context).inflate(R.layout.row_filter_child, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(
        holder: NavigationItemViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        holder.itemView.checkbox.isChecked = itemList[position].isCheck
        holder.itemView.title.text = itemList[position].title
        holder.itemView.lyMain.setOnClickListener {
            currentPos = position
            itemList[position].isCheck = !itemList[position].isCheck
            notifyDataSetChanged()
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    itemList = items
                } else {
                    val resultList = ArrayList<FilterChildModel>()
                    for (row in items) {
                        if (row.title.trim().lowercase().contains(charSearch.lowercase().trim())
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
                itemList = results?.values as ArrayList<FilterChildModel>
                notifyDataSetChanged()
            }
        }
    }
}
