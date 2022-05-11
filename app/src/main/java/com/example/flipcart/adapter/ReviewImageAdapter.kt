package com.example.flipcart.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flipcart.R
import com.example.flipcart.model.NavigationItemModel
import kotlinx.android.synthetic.main.row_nav_drawer.view.*
import kotlinx.android.synthetic.main.row_review_image.view.*

class ReviewImageAdapter(private var items: ArrayList<String>) :
    RecyclerView.Adapter<ReviewImageAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem = LayoutInflater.from(parent.context).inflate(R.layout.row_review_image, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        Glide
            .with(context)
            .load(items[position])
            .centerCrop()
            .into(holder.itemView.imgReview);
    }

}
