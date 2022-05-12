package com.webecom.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.webecom.R
import com.webecom.adapter.FilterAdapter
import com.webecom.adapter.FilterChildAdapter
import com.webecom.model.FilterChildModel
import com.webecom.utils.ClickListener
import com.webecom.utils.RecyclerTouchListener
import com.webecom.utils.Utils
import kotlinx.android.synthetic.main.activity_filter.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class FilterActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewChild: RecyclerView
    private lateinit var searchBar: SearchView
    private lateinit var childAdapter : FilterChildAdapter
    private var items2 = Utils.getDummyFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        init()
    }

    private fun init() {
        imgBack.visibility = View.VISIBLE
        tvClear.visibility = View.VISIBLE
        headerText.text = "Filters"

        searchBar =findViewById(R.id.searchBar)
        searchBar.setIconifiedByDefault(false)
        searchBar.queryHint = resources.getString(R.string.search_hint)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerViewChild = findViewById(R.id.recyclerViewChild)
        var filterAdapter = FilterAdapter(items2)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = filterAdapter
        recyclerView.isNestedScrollingEnabled = false;

        childAdapter= FilterChildAdapter(items2[0].subarray as ArrayList<FilterChildModel>)
        recyclerViewChild.layoutManager = LinearLayoutManager(this)
        recyclerViewChild.setHasFixedSize(true)
        recyclerViewChild.adapter = childAdapter
        recyclerViewChild.isNestedScrollingEnabled = false;

        imgBack.setOnClickListener(this)
        tvClear.setOnClickListener(this)
        applyCard.setOnClickListener(this)

        recyclerView.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                if (items2[position].subarray.size > 0) {
                    childAdapter= FilterChildAdapter(items2[position].subarray as ArrayList<FilterChildModel>)
                    recyclerViewChild.layoutManager = LinearLayoutManager(this@FilterActivity)
                    recyclerViewChild.adapter = childAdapter
                }
            }
        }))
        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                childAdapter.let {
                    childAdapter.filter.filter(newText)
                }
                return false
            }
        })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBack -> {
                onBackPressed()
            }
            R.id.tvClear -> {
                finish()
            }
            R.id.applyCard -> {
                finish()
            }
            else -> {
            }
        }
    }

}