package com.webecom.adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.model.ProductModel
import kotlinx.android.synthetic.main.row_cart_product.view.*
import android.widget.AdapterView
import androidx.core.content.ContextCompat


class CartProductAdapter(private var items: ArrayList<ProductModel>) :
    RecyclerView.Adapter<CartProductAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context
    var itemList = ArrayList<ProductModel>()
    var list_of_items = arrayOf("1", "2", "3")

    init {
        this.itemList = items
    }

    class NavigationItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val navItem =
            LayoutInflater.from(parent.context).inflate(R.layout.row_cart_product, parent, false)
        return NavigationItemViewHolder(navItem)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        //holder.itemView.tvRating.text = itemList[position].review
        //holder.itemView.title.text = itemList[position].title
        var data=items[position]
        holder.itemView.tvOldPrice.paintFlags =
            holder.itemView.tvOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


        holder.itemView.tvCount.text="${data.qty}"
        if (data.qty>1){
            holder.itemView.imgMinus.isEnabled = true
            holder.itemView.imgMinus.setColorFilter(ContextCompat.getColor(context, R.color.blue))
        }else{
            holder.itemView.imgMinus.isEnabled = false
            holder.itemView.imgMinus.setColorFilter(ContextCompat.getColor(context, R.color.blue_lite))
        }
        holder.itemView.imgMinus.isEnabled = data.qty>1
        holder.itemView.imgMinus.setOnClickListener {
            if (data.qty>1){
                data.qty--
            }
            notifyItemChanged(position)
        }
        holder.itemView.imgAdd.setOnClickListener {
            data.qty++
            notifyItemChanged(position)
        }
    }

}
