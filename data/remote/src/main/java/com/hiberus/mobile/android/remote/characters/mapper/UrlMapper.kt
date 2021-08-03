package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.UrlBo
import com.hiberus.mobile.android.remote.characters.dto.UrlDto

internal fun List<UrlDto>.toBo() = this.map { it.toBo() }

private fun UrlDto.toBo() = UrlBo(
    type,
    url
)