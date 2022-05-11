package com.example.flipcart.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flipcart.R
import com.example.flipcart.adapter.FilterChildAdapter
import com.example.flipcart.adapter.ReviewFilterAdapter
import com.example.flipcart.adapter.ReviewsAdapter
import com.example.flipcart.model.FilterChildModel
import com.example.flipcart.utils.ClickListener
import com.example.flipcart.utils.RecyclerTouchListener
import com.example.flipcart.utils.Utils
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_filter.recyclerView
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.toolbar.*

class ReviewActivity : AppCompatActivity(), View.OnClickListener {

    private var items2 = Utils.getReviews()
    private lateinit var reviewAdapter : ReviewsAdapter
    private var items3 = Utils.getReviewsFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        headerText.text = "All Reviews"

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        reviewAdapter = ReviewsAdapter(items2,false)
        recyclerView.adapter = reviewAdapter


        recyclerViewFilter.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewFilter.setHasFixedSize(true)
        val reviewFilterAdapter = ReviewFilterAdapter(items3)
        recyclerViewFilter.adapter = reviewFilterAdapter

        recyclerViewFilter.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {

                if (position==0){
                    reviewAdapter.filter.filter("")
                }else if (position==1){
                    reviewAdapter.filter.filter("5")
                }else if (position==2){
                    reviewAdapter.filter.filter("4")
                }else if (position==3){
                    reviewAdapter.filter.filter("3")
                }else if (position==4){
                    reviewAdapter.filter.filter("2")
                }else if (position==5){
                    reviewAdapter.filter.filter("1")
                }
                Log.e("selected",""+position+"  "+reviewAdapter.itemCount)
                if (reviewAdapter.itemList.size==0){
                    tvNoRecordFound.visibility=View.VISIBLE
                }else{
                    tvNoRecordFound.visibility=View.GONE
                }

            }
        }))
        imgBack.setOnClickListener(this)



    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
            else -> {
            }
        }
    }

}