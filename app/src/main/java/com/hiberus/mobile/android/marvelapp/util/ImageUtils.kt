package com.hiberus.mobile.android.marvelapp.util

private const val SLASH = "/"
private const val DOT = "."

fun getImageUrl(path: String, extenstion: String, variant: String) =
    "$path$SLASH$variant$DOT$extenstion"

