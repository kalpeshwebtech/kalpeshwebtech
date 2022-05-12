package com.webecom.model

data class LoginResponce(
    val IsSuccess: Boolean,
    val Message: String,
    val Data: LoginData,
)