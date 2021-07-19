package com.hiberus.mobile.android.remote.utils

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun getDate(): Date = Date.from(
    LocalDate
        .of(2021, 7, 15)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
)

fun readResourceFile(path: String) =
    object {}.javaClass.classLoader?.getResource(path)?.readText() ?: ""