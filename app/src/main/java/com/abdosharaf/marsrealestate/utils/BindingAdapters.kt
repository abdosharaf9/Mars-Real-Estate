package com.abdosharaf.marsrealestate.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abdosharaf.marsrealestate.R
import com.abdosharaf.marsrealestate.listScreen.MarsAdapter
import com.abdosharaf.marsrealestate.network.MarsItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.abdosharaf.marsrealestate.utils.Constants.Companion.MarsApiStatus

@BindingAdapter("setImage")
fun ImageView.setImage(url: String) {

    val imgUrl = url.toUri().buildUpon().scheme("https").build()

    Glide.with(this.context).load(imgUrl)
        .transform(CenterCrop(), RoundedCorners(50))
        .apply(
            RequestOptions()
                .error(R.drawable.broken_image)
                .placeholder(R.drawable.loading_animation)
        ).into(this)
}

@BindingAdapter("getType")
fun TextView.getType(item: MarsItem) {
    this.text = when (item.isRental) {
        true -> this.resources.getString(
            R.string.display_type,
            this.resources.getString(R.string.rent)
        )
        false -> this.resources.getString(
            R.string.display_type,
            this.resources.getString(R.string.sale)
        )
    }
}

@BindingAdapter("getPrice")
fun TextView.getPrice(item: MarsItem) {
    this.text = when (item.isRental) {
        true -> this.resources.getString(R.string.display_price_monthly_rental, item.price)
        false -> this.resources.getString(R.string.display_price, item.price)
    }
}

@BindingAdapter("dataList")
fun RecyclerView.dataList(list: List<MarsItem>?) {
    if(list.isNullOrEmpty()) {
        this.visibility = View.GONE
    } else {
        this.visibility = View.VISIBLE
        (this.adapter as MarsAdapter).submitList(list)
    }
}

@BindingAdapter("getMarsApiStatus")
fun ImageView.getMarsApiStatus(status: MarsApiStatus) {
    when (status) {
        MarsApiStatus.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        MarsApiStatus.ERROR -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.connection_error)
        }
        MarsApiStatus.DONE -> {
            this.visibility = View.GONE
        }
    }
}