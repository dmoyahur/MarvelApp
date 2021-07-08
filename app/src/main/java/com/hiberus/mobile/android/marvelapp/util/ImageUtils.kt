package com.hiberus.mobile.android.marvelapp.util

private const val SLASH = "/"
private const val DOT = "."
private const val HTTP = "http"
private const val HTTPS = "https"

/*
fun getImageUrl(path: String, extenstion: String, aspectRatio: String) =
    "${path.replace(HTTP, HTTPS)}$SLASH$aspectRatio$DOT$extenstion"*/

fun getImageUrl(path: String, extenstion: String, variant: String) =
    "$path$SLASH$variant$DOT$extenstion"

