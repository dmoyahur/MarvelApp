package com.hiberus.mobile.android.local.utils

import java.time.LocalDate
import java.time.ZoneId
import java.util.*

fun getDate(): Date = Date.from(
    LocalDate
        .of(2021, 7, 15)
        .atStartOfDay(ZoneId.systemDefault())
        .toInstant()
)