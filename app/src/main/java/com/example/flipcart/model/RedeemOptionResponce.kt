package com.webecom.model

import java.io.Serializable

data class RedeemOptionResponce(
    val IsSuccess: Boolean,
    val Message: String,
    val Data: RedeemData,
):Serializable