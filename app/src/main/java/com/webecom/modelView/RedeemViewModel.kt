package com.webecom.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.webecom.model.LoginResponce
import com.webecom.model.RedeemOptionResponce
import com.webecom.pref.Prefkeys.*
import com.webecom.service.ApiPoints
import com.webecom.service.ApiService
import com.webecom.utils.SingleLiveEvent
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