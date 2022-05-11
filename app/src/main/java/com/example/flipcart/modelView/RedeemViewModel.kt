package com.example.flipcart.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flipcart.model.LoginResponce
import com.example.flipcart.model.RedeemOptionResponce
import com.example.flipcart.pref.Prefkeys.*
import com.example.flipcart.service.ApiPoints
import com.example.flipcart.service.ApiService
import com.example.flipcart.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class RedeemViewModel : ViewModel() , Callback<RedeemOptionResponce> {
    var isLoader= SingleLiveEvent<Boolean>()
    lateinit var apiInterface: ApiPoints
    val redeemOption= MutableLiveData<RedeemOptionResponce>()
    val error= MutableLiveData<String>()
    init {
        apiInterface = ApiService().client.create(ApiPoints::class.java)
    }

    fun getRedeemOptions() {
        isLoader?.value = true
        apiInterface.GETREDDEMOPTIONS().enqueue(this)
    }

    override fun onResponse(call: Call<RedeemOptionResponce>, response: Response<RedeemOptionResponce>) {
        isLoader?.value = false
        redeemOption?.value=response?.body()

    }

    override fun onFailure(call: Call<RedeemOptionResponce>, t: Throwable) {
        isLoader?.value = false
        error?.value=call.toString()
    }

}