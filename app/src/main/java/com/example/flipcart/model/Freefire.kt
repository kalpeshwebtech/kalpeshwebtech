package com.webecom.model

import java.io.Serializable

data class Freefire(
    val name: String,
    val icon: String,
    val coin_amount: List<CoinAmount> = arrayListOf(),
): Serializable
