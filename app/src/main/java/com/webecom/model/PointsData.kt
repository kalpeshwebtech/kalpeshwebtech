package com.webecom.model

import java.io.Serializable

data class PointsData (
     val id : Int,
      val end_user_id : String,
     val amount : String,
     val phone : String,
     val updated_at : String,
     val created_at : String,
):Serializable