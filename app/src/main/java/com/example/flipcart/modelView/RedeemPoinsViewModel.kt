package com.example.flipcart.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flipcart.model.LoginResponce
import com.example.flipcart.model.RedeemPointsResponce
import com.example.flipcart.pref.Prefkeys.*
import com.example.flipcart.service.ApiPoints
import com.example.flipcart.service.ApiService
import com.example.flipcart.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RedeemPoinsViewModel : ViewModel() , Callback<RedeemPointsResponce> {
    var isLoader= SingleLiveEvent<Boolean>()
    lateinit var apiInterface: ApiPoints
    val pointsData= MutableLiveData<RedeemPointsResponce>()
    val error= MutableLiveData<String>()
    init {
        apiInterface = ApiService().client.create(ApiPoints::class.java)
    }

    fun redeemPoints(map: HashMap<String, String>) {
        isLoader?.value = true
        apiInterface.REDEEMCOIN(map.get(END_USER_ID),map.get(PHONE),map.get(AMOUNT)).enqueue(this)
    }

    override fun onResponse(call: Call<RedeemPointsResponce>, response: Response<RedeemPointsResponce>) {
        isLoader?.value = false
        pointsData?.value=response?.body()
    }

    override fun onFailure(call: Call<RedeemPointsResponce>, t: Throwable) {
        isLoader?.value = false
        error?.value=call.toString()
    }

}