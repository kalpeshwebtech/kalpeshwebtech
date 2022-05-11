package com.pti.coindhan.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flipcart.model.SpinResponce
import com.example.flipcart.service.ApiPoints
import com.example.flipcart.service.ApiService
import com.example.flipcart.utils.SingleLiveEvent
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