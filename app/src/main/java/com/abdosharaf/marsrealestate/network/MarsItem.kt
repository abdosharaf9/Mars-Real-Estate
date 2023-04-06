package com.abdosharaf.marsrealestate.network

import java.io.Serializable

data class MarsItem(
    val id: String,
    val img_src: String,
    val price: Double,
    val type: String
) : Serializable {
    val isRental
        get() = type == "rent"
}