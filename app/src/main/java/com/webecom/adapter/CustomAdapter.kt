package com.webecom.adapter

import androidx.viewpager.widget.ViewPager

import android.view.ViewGroup
import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView

import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.webecom.R


class CustomAdapter(
    private val activity: Context,
    private val imagesArray: Array<Int>,
) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = (activity as Activity).layoutInflater
        val viewItem: View = inflater.inflate(R.layout.row_image, container, false)
        val imageView: ImageView = viewItem.findViewById(R.id.imageView) as ImageView

        Glide
            .with(activity)
            .load(imagesArray[position])
            .centerCrop()
            .into(imageView);
        (container as ViewPager).addView(viewItem)
        return viewItem
    }

    override fun getCount(): Int {
        return imagesArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as View)
    }
}