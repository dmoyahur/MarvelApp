package com.hiberus.mobile.android.marvelapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(
    context: Context,
    url: String,
    options: RequestOptions? = null
) {
    Glide.with(context)
        .load(url)
        .apply {
            options?.let {
                this.apply(it)
            }
        }
        .into(this)
}