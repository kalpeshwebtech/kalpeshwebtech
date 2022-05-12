package com.pti.coindhan.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.webecom.model.SpinResponce
import com.webecom.service.ApiPoints
import com.webecom.service.ApiService
import com.webecom.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpinViewModel : ViewModel() , Callback<SpinResponce> {
    var isLoader= SingleLiveEvent<Boolean>()
    var apiInterface: ApiPoints
    val spinResponce= MutableLiveData<SpinResponce>()
    val error= MutableLiveData<String>()
    init {
        apiInterface = ApiService().client.create(ApiPoints::class.java)
    }

    fun addcoin(uid: String, title: String,coins: String) {
        isLoader.value = true
        apiInterface.ADDCOINT(uid,title,coins).enqueue(this)
    }

    override fun onResponse(call: Call<SpinResponce>, response: Response<SpinResponce>) {
        isLoader.value = false
        spinResponce.value=response.body()

    }

    override fun onFailure(call: Call<SpinResponce>, t: Throwable) {
        isLoader.value = false
        error.value=call.toString()
    }

}