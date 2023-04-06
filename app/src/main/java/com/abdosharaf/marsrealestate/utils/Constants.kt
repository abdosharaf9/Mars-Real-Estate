package com.abdosharaf.marsrealestate.utils

class Constants {
    companion object {
        const val BASE_URL = "https://mars.udacity.com/"
        const val TAG = "ABDO_SHARAF"

        enum class MarsApiStatus {
            LOADING, DONE, ERROR
        }
        enum class MarsApiFilter(val value: String) { SHOW_RENT("rent"), SHOW_BUY("buy"), SHOW_ALL("all") }
    }
}