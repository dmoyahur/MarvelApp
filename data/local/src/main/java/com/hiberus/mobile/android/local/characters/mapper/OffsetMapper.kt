package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.OffsetDbo

object OffsetMapper {

    fun map(offset: Int) = OffsetDbo(offset)
}