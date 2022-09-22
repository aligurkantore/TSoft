package com.codingurkan.tsoft.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codingurkan.tsoft.R

fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_no_photo)
        .into(this)
}


