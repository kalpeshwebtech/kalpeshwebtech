package com.example.flipcart.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.flipcart.R
import com.example.flipcart.model.FilterModel
import com.example.flipcart.model.NavigationItemModel
import kotlinx.android.synthetic.main.row_filter.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import kotlinx.android.synthetic.main.row_nav_drawer.view.navigation_icon

class FilterAdapter(private var items: ArrayList<FilterModel>) :
    RecyclerView.Adapter<FilterAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    private var currentPos: Int=0
    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_filter, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, @SuppressLint("RecyclerView") position: Int) {
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            holder.itemView.title.setTextColor(ContextCompat.getColor(context, R.color.purple_500))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.trans_gray))
            holder.itemView.title.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
        holder.itemView.title.text = items[position].title
        holder.itemView.lyMain.setOnClickListener {
            currentPos=position
            notifyDataSetChanged()
        }

    }

}
