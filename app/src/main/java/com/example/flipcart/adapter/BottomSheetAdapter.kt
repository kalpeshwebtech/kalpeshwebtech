package com.example.flipcart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flipcart.R


class BottomSheetAdapter(var ctx: Context, var array: ArrayList<String>,var selected:Int) : RecyclerView.Adapter<BottomSheetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.bottom_sheet_row, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.checkRadio.isChecked = selected==position
        val strTitle: String = array[position];
        holder.txtTitle.text =strTitle
        holder.itemView.setOnClickListener { v ->
        }



    }

    override fun getItemCount(): Int {
        return array.size
    }

    open class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var txtTitle : TextView = v.findViewById(R.id.tvTitle)
        var checkRadio : RadioButton = v.findViewById(R.id.checkRadio)

        init {
        }

        override fun onClick(v: View) {

        }

        companion object {
        }
    }
}

