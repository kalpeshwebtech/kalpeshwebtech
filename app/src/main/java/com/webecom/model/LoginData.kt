package com.webecom.model

data class LoginData (
     val id : Int,
      val name : String,
     val email : String,
     val phone : String,
     val referral_code : String,
     val referral_by : String,
     val image : String,
     val social_id : String,
     val device_info : String,
     val is_register : Int,
     val created_at : String,
     val updated_at : String,
)