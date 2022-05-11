package com.example.flipcart.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flipcart.R
import com.example.flipcart.activity.ProductActivity
import com.example.flipcart.model.CategoryModel
import com.example.flipcart.model.NavigationItemModel
import kotlinx.android.synthetic.main.row_nav_drawer.view.*

class CategoryAdapter(private var items: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_category, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        holder.itemView.navigation_title.text = items[position].title

        Glide
            .with(holder.itemView.context)
            .load(items[position].icon)
            .centerCrop()
            .into(holder.itemView.navigation_icon);
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, ProductActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }
    }

}
