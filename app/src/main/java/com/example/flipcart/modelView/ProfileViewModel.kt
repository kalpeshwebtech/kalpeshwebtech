package com.example.flipcart.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flipcart.model.LoginResponce
import com.example.flipcart.pref.Prefkeys.*
import com.example.flipcart.service.ApiPoints
import com.example.flipcart.service.ApiService
import com.example.flipcart.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ProfileViewModel : ViewModel() , Callback<LoginResponce> {
    var isLoader= SingleLiveEvent<Boolean>()
    lateinit var apiInterface: ApiPoints
    val userLogin= MutableLiveData<LoginResponce>()
    val error= MutableLiveData<String>()
    init {
        apiInterface = ApiService().client.create(ApiPoints::class.java)
    }

    fun updateProfile(map: HashMap<String, String>) {
        isLoader?.value = true
        apiInterface.COMPLATEPROFILE(map.get(ID),map.get(PHONE),map.get(REFERRAL_BY)).enqueue(this)
    }

    override fun onResponse(call: Call<LoginResponce>, response: Response<LoginResponce>) {
        isLoader?.value = false
        userLogin?.value=response?.body()
    }

    override fun onFailure(call: Call<LoginResponce>, t: Throwable) {
        isLoader?.value = false
        error?.value=call.toString()
    }

}