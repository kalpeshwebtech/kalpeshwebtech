package com.webecom.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.webecom.R
import com.webecom.adapter.CategoryAdapter
import com.webecom.adapter.CustomAdapter
import com.webecom.adapter.CategoryProductAdapter
import com.webecom.model.CategoryModel
import com.webecom.model.ProductModel
import com.rd.PageIndicatorView
import java.util.*


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var viewOfLayout: View
    private lateinit var searchBar: SearchView
    private lateinit var viewPager: ViewPager
    private lateinit var pageIndicatorView: PageIndicatorView
    private lateinit var customAdapter: CustomAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView
    var imageId =
        arrayOf<Int>(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7
        )
    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500

    val PERIOD_MS: Long = 3000
    var dotscount = 0
    private var path:String="https://cdn-icons-png.flaticon.com/512/2097/2097276.png"
    var path2:String="https://cdn-icons-png.flaticon.com/512/3170/3170733.png"
    private var items = arrayListOf(
        CategoryModel(path, "Top Offer"),
        CategoryModel(path2, "Mobile & Tablets"),
        CategoryModel(path, "Electronics & Accessories"),
        CategoryModel(path2, "TVs & Appliances"),
        CategoryModel(path, "Beauty,Food,Toys, & More"),
        CategoryModel(path2, "New launches"),
        CategoryModel(path, "Home & Furniture"),
        CategoryModel(path2, "Fashion"),
        CategoryModel(path, "Travel"),
        CategoryModel(path, "Furniture"),
    )
    private var items2 = arrayListOf(
        ProductModel("1",R.drawable.p1, "Top Offer","No Cost EMI",1),
        ProductModel("2",R.drawable.p3, "Mobile & Tablets","Best selling product",2),
        ProductModel("3",R.drawable.p5, "Electronics & Accessories","F-Assured",3),
        ProductModel("4",R.drawable.p7, "TVs & Appliances","4 Stars & Above",2),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }*/
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewOfLayout = inflater.inflate(R.layout.fragment_home, container, false)

        inits()
        return viewOfLayout
    }

    private fun inits() {
        viewPager = viewOfLayout.findViewById(R.id.viewPager)
        pageIndicatorView = viewOfLayout.findViewById(R.id.pageIndicatorView)
        recyclerView = viewOfLayout.findViewById(R.id.recyclerView)
        recyclerView2 = viewOfLayout.findViewById(R.id.recyclerView2)
        recyclerView3 = viewOfLayout.findViewById(R.id.recyclerView3)
        recyclerView4 = viewOfLayout.findViewById(R.id.recyclerView4)
        searchBar = viewOfLayout.findViewById(R.id.searchBar)
        searchBar.setIconifiedByDefault(false)
        searchBar.queryHint = resources.getString(R.string.search_hint)

        setUpViewPager()
        recyclerView.layoutManager = GridLayoutManager(context, 5)
        categoryAdapter=CategoryAdapter(items)
        recyclerView.adapter=categoryAdapter
        recyclerView.isNestedScrollingEnabled = false;

        recyclerView2.layoutManager = GridLayoutManager(context, 5)
        recyclerView2.adapter=categoryAdapter
        recyclerView2.isNestedScrollingEnabled = false;

        //recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var productAdapter= CategoryProductAdapter(items2);
        recyclerView3.layoutManager = GridLayoutManager(context, 2)
        recyclerView3.adapter=productAdapter
        recyclerView3.isNestedScrollingEnabled = false;

        //recyclerView3.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        var productAdapter2= CategoryProductAdapter(items2);
        recyclerView4.layoutManager = GridLayoutManager(context, 2)
        recyclerView4.adapter=productAdapter2
        recyclerView4.isNestedScrollingEnabled = false;
    }

    private fun setUpViewPager() {
        customAdapter = CustomAdapter(requireContext(), imageId)
        viewPager.adapter = customAdapter
        val handler = Handler()
        val Update = Runnable {
            if (currentPage ===customAdapter.count - 1) {
                currentPage = 0
            }
            pageIndicatorView.setSelection(currentPage)
            viewPager.setCurrentItem(currentPage++, true)
        }
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
        dotscount = customAdapter.count
        pageIndicatorView.setCount(dotscount)
        pageIndicatorView.setSelection(0)
    }


}