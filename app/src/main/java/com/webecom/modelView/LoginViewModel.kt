package com.webecom.modelView

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.webecom.model.LoginResponce
import com.webecom.pref.Prefkeys.DEVICE_INFO
import com.webecom.pref.Prefkeys.EMAIL
import com.webecom.pref.Prefkeys.IMAGE
import com.webecom.pref.Prefkeys.NAME
import com.webecom.pref.Prefkeys.SOCIAL_ID

import com.webecom.service.ApiPoints
import com.webecom.service.ApiService
import com.webecom.utils.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginViewModel : ViewModel() , Callback<LoginResponce> {
    var isLoader= SingleLiveEvent<Boolean>()
    lateinit var apiInterface: ApiPoints
    val userLogin= MutableLiveData<LoginResponce>()
    val error= MutableLiveData<String>()
    init {
        apiInterface = ApiService().client.create(ApiPoints::class.java)
    }

    fun login(user: HashMap<String, String>, token: String) {
        isLoader?.value = true
        apiInterface.LOGIN(user.get(NAME),user.get(EMAIL),user.get(IMAGE),user.get(SOCIAL_ID),user.get(DEVICE_INFO)).enqueue(this)
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