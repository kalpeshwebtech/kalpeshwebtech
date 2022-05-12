package com.webecom.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import com.webecom.R
import com.webecom.model.*
import java.util.regex.Pattern

class Utils {
    companion object {
        var cartArray = ArrayList<ProductModel>()
        fun isInternet(context: Context): Boolean {
            var result = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val networkCapabilities = connectivityManager.activeNetwork ?: return false
                val actNw =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
                result = when {
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } else {
                connectivityManager.run {
                    connectivityManager.activeNetworkInfo?.run {
                        result = when (type) {
                            ConnectivityManager.TYPE_WIFI -> true
                            ConnectivityManager.TYPE_MOBILE -> true
                            ConnectivityManager.TYPE_ETHERNET -> true
                            else -> false
                        }

                    }
                }
            }

            return result
        }

        fun getDeviceInfo(context: Context): String? {
            var androidId = ""
            if (androidId == "") {
                androidId =
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            }
            var imei = "3481156846581195"
            val manufacturer = "manufacturer"
            val model = "model"
            val serial = "12312312"
            imei = try {
                "3481156846581195"
            } catch (e: Exception) {
                e.message
                "3481156846581195"
            }
            var versionName = ""
            versionName = try {
                val manager = context.packageManager
                val info = manager.getPackageInfo(context.packageName, 0)
                info.versionName
            } catch (e: Exception) {
                "2.4.4"
            }
            if (versionName == null || versionName == "") {
                versionName = "unknown"
            }
            return ("name=" + manufacturer
                    + "||model=" + model +
                    "||imei=" + imei +
                    "||version=" + versionName +
                    "||udid=" + androidId)
        }

        fun showMessage(context: Context, it: String?) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }

        fun isValidEmail(email: String): Boolean {
            val emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}"
            val pat: Pattern = Pattern.compile(emailPattern)
            return if (email == null) {
                false
            } else {
                pat.matcher(email).matches()
            }
        }

        fun getNavigation(): ArrayList<NavigationItemModel> {
            val items = arrayListOf(
                NavigationItemModel(R.drawable.ic_home, "Home", false),
                NavigationItemModel(R.drawable.ic_music, "Music", true),
                NavigationItemModel(R.drawable.ic_movie, "Movies", false),
                NavigationItemModel(R.drawable.ic_book, "Books", false),
                NavigationItemModel(R.drawable.ic_book, "My Orders", false),
                NavigationItemModel(R.drawable.ic_profile, "Profile", true),
                NavigationItemModel(R.drawable.ic_settings, "Settings", true),
                NavigationItemModel(R.drawable.ic_social, "Like us on facebook", false)
            )
            return items
        }

        fun getDummyFilter(): ArrayList<FilterModel> {
            var items2 = arrayListOf(
                FilterModel(
                    "Price", arrayListOf(
                        FilterChildModel("RS. 2000 and Below", false),
                        FilterChildModel("RS. 2001 - Rs. 3999", false),
                        FilterChildModel("RS. 4000 - Rs. 6999", false),
                        FilterChildModel("RS. 7000 - Rs. 9999", false),
                        FilterChildModel("RS. 10000 - Rs. 12999", false),
                        FilterChildModel("RS. 13000 - Rs. 15999", false),
                        FilterChildModel("RS. 16000 - Rs. 19999", false),
                        FilterChildModel("RS. 20000 - Rs. 24999", false),
                        FilterChildModel("RS. 25000 - Rs. 29999", false),
                        FilterChildModel("RS. 30000 - Rs. 49999", false),
                        FilterChildModel("RS. 50000 and Above", false),
                    )
                ),
                FilterModel(
                    "Brand", arrayListOf(
                        FilterChildModel("realme", false),
                        FilterChildModel("POCO", false),
                        FilterChildModel("Infinix", false),
                        FilterChildModel("SAMSUNG", false),
                        FilterChildModel("MI", false),
                        FilterChildModel("APPLE", false),
                        FilterChildModel("acer", false),
                        FilterChildModel("Alcate", false),
                    )
                ),
                FilterModel(
                    "Customer Ratings", arrayListOf(
                        FilterChildModel("4 * & Above", false),
                        FilterChildModel("3 * & Above", false),
                        FilterChildModel("2 * & Above", false),
                        FilterChildModel("1 * & Above", false),
                    )
                ),
                FilterModel(
                    "GST Invoice Available", arrayListOf(
                        FilterChildModel("GST invoice Available", false),
                    )
                ),
                FilterModel(
                    "RAM", arrayListOf(
                        FilterChildModel("4 GB", false),
                        FilterChildModel("3 GB", false),
                        FilterChildModel("2 GB", false),
                        FilterChildModel("1 GB and Below", false),
                        FilterChildModel("6 GB", false),
                        FilterChildModel("8 GB and Above", false),
                    )
                ),
                FilterModel(
                    "Internal Storage", arrayListOf(
                        FilterChildModel("256 GB & Above", false),
                        FilterChildModel("128 - 255.9 GB", false),
                        FilterChildModel("64 - 127.9 GB", false),
                        FilterChildModel("32 - 63.9 GB", false),
                        FilterChildModel("16 - 31.9 GB", false),
                        FilterChildModel("8 - 15.9 GB", false),
                        FilterChildModel("4 - 7.9 GB", false),
                        FilterChildModel("2 - 3.9 GB", false),
                        FilterChildModel("1 - 1.9 GB", false),
                        FilterChildModel("Less than 1 GB", false),
                    )
                ),
                FilterModel(
                    "Battery Capacity", arrayListOf(
                        FilterChildModel("1000 - 1999 mAh", false),
                        FilterChildModel("2000 - 2999 mAh", false),
                        FilterChildModel("3000 - 3999 mAh", false),
                        FilterChildModel("4000 - 4999 mAh", false),
                        FilterChildModel("5000 - 5999 mAh", false),
                        FilterChildModel("6000 mAh & Above", false),
                        FilterChildModel("Less than 1000 mAh", false),
                    )
                ),
                FilterModel(
                    "Screen Size", arrayListOf(
                        FilterChildModel("5.7 - 5.9 inch", false),
                        FilterChildModel("5.5 - 5.6 inch", false),
                        FilterChildModel("5.2 - 5.4 inch", false),
                        FilterChildModel("5 - 5.1 inch", false),
                        FilterChildModel("3 - 3.4 inch", false),
                        FilterChildModel("Less than 3 inch", false),
                        FilterChildModel("6.4 inch & Above", false),
                        FilterChildModel("6 - 6.3 inch", false),
                    )
                ),
                FilterModel(
                    "Primary Camera", arrayListOf(
                        FilterChildModel("5 - 7.9 MP", false),
                        FilterChildModel("Below 5 MP", false),
                        FilterChildModel("16 - 20.9 MP", false),
                        FilterChildModel("32 - 47.9 MP", false),
                        FilterChildModel("64 MP & Above", false),
                    )
                ),
                FilterModel("Secondary Camera", arrayListOf()),
                FilterModel("Processsor Brand", arrayListOf()),
                FilterModel("Speciality", arrayListOf()),
                FilterModel("Resolution Type", arrayListOf()),
                FilterModel("Operating System", arrayListOf()),
                FilterModel("Network Type", arrayListOf()),
                FilterModel("Sim Type", arrayListOf()),
                FilterModel("Offers", arrayListOf()),
                FilterModel("Budget", arrayListOf()),
                FilterModel("Features", arrayListOf()),
                FilterModel("Type", arrayListOf()),
                FilterModel("Number of Cores", arrayListOf()),
                FilterModel("Availability", arrayListOf()),
                FilterModel("Discount", arrayListOf()),
                FilterModel("Category", arrayListOf()),
            )
            return items2
        }

        fun getReviewsFilter(): ArrayList<String> {
            val items2 = arrayListOf(
                "All Reviews",
                "5 Rating",
                "4 Rating",
                "3 Rating",
                "2 Rating",
                "1 Rating"
            )
            return items2
        }

        fun getReviews(): ArrayList<ReviewsModel> {
            val items2 = arrayListOf(
                ReviewsModel(
                    "4",
                    "Value-for-money",
                    "Rajib Pain, Bankura District",
                    "156", "20",
                    "is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,",
                    imgarray
                ),
                ReviewsModel(
                    "5",
                    "Wonderful",
                    "Pallab Saha, Bhatpara", "230", "122",
                    "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form",
                    imgarray
                ),
                ReviewsModel(
                    "3",
                    "Good choice",
                    "Kaushik Pal, North Barrackpur",
                    "134", "45",
                    "All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary",
                    imgarray
                ),
                ReviewsModel(
                    "2",
                    "Mind-blowing purchase",
                    "Aniket Kumar, okaro District",
                    "35", "6",
                    "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old",
                    imgarray
                ),
                ReviewsModel(
                    "4",
                    "Must buy!",
                    "CicilyS, Pathanamthitta District",
                    "470", "90",
                    "The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from \"de Finibus Bonorum et Malorum\" by Cicero are also reproduced in their exact original form",
                    imgarray
                ),
                ReviewsModel(
                    "3",
                    "Best in the market!",
                    "Debasis Patnaik, Bhubaneswa",
                    "111", "12",
                    "Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
                    imgarray
                ),
                ReviewsModel(
                    "2",
                    "Really Nice",
                    "Tularam dansana, Jharsuguda District",
                    "436", "68",
                    "If you are going to use a passage of Lorem Ipsum",
                    imgarray
                ),
            )
            return items2
        }

        fun getProduct(): ArrayList<ProductModel> {
            val items2 = arrayListOf(
                ProductModel("1", R.drawable.p1, "Top Offer", "No Cost EMI", 1),
                ProductModel("2", R.drawable.p3, "Mobile & Tablets", "Best selling product", 1),
                ProductModel("3", R.drawable.p5, "Electronics & Accessories", "F-Assured", 7),
                ProductModel("4", R.drawable.p7, "TVs & Appliances", "4 Stars & Above", 2),
                ProductModel("5", R.drawable.p2, "Top Offer", "No Cost EMI", 1),
                ProductModel("6", R.drawable.p4, "Mobile & Tablets", "Best selling product", 4),
                ProductModel("7", R.drawable.p6, "Electronics & Accessories", "F-Assured", 1),
                ProductModel("8", R.drawable.p1, "TVs & Appliances", "4 Stars & Above", 6),
                ProductModel("9", R.drawable.p5, "Electronics & Accessories", "F-Assured", 1),
                ProductModel("10", R.drawable.p7, "TVs & Appliances", "4 Stars & Above", 2),
                ProductModel("11", R.drawable.p2, "Top Offer", "No Cost EMI", 4),
                ProductModel("12", R.drawable.p4, "Mobile & Tablets", "Best selling product", 3),
            )
            return items2
        }

        fun getSortBy(): ArrayList<String> {
            val item2 = arrayListOf(
                "Relevance",
                "Popularity",
                "Price - Low to High",
                "Price - High to Low",
                "Newest First"
            )
            return item2
        }
        fun getCart(): ArrayList<ProductModel> {
            return cartArray
        }
        fun addToCart(){
            val rnds = (0..getProduct().size-1).random()
            cartArray.add(getProduct()[rnds])
        }
        var imgarray = arrayListOf(
            "https://images.pexels.com/photos/60597/dahlia-red-blossom-bloom-60597.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/7817553/pexels-photo-7817553.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/10267671/pexels-photo-10267671.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/4606065/pexels-photo-4606065.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/10837099/pexels-photo-10837099.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/6211241/pexels-photo-6211241.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
            "https://images.pexels.com/photos/8877365/pexels-photo-8877365.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        )
    }


}