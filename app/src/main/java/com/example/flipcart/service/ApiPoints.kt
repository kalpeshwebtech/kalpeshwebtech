package com.webecom.service

import com.webecom.model.LoginResponce
import com.webecom.model.RedeemOptionResponce
import com.webecom.model.RedeemPointsResponce
import com.webecom.model.SpinResponce
import com.webecom.pref.Prefkeys.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiPoints {

    @FormUrlEncoded
    @POST(REGISTER_LOGIN)
    fun LOGIN(@Field(NAME) name: String?, @Field(EMAIL) email: String?, @Field(IMAGE) image: String?,
              @Field(SOCIAL_ID) socialid: String?, @Field(DEVICE_INFO) deviceid: String?): Call<LoginResponce>

    @FormUrlEncoded
    @POST(ADD_COIN)
    fun ADDCOINT(@Field(END_USER_ID) user_id: String?, @Field(TITLE) title: String?, @Field(AMOUNT) amount: String?): Call<SpinResponce>

    @FormUrlEncoded
    @POST(COMPLATE_PROFILE)
    fun COMPLATEPROFILE(@Field(ID) id: String?, @Field(PHONE) phone: String?, @Field(REFERRAL_BY) referral_by: String?): Call<LoginResponce>

    @FormUrlEncoded
    @POST(REDEEM_COIN)
    fun REDEEMCOIN(@Field(END_USER_ID) user_id: String?, @Field(AMOUNT) amount: String?, @Field(PHONE) phone: String?): Call<RedeemPointsResponce>

    @GET(GET_REDEEM_OPTIONS)
    fun GETREDDEMOPTIONS(): Call<RedeemOptionResponce>
}