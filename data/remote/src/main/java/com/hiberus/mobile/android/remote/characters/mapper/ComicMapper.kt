package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.bo.ComicBo
import com.hiberus.mobile.android.remote.characters.dto.ComicListDto
import com.hiberus.mobile.android.remote.characters.dto.ComicSummaryDto


internal fun ComicListDto.toBo() = this.items.map { it.toBo() }

private fun ComicSummaryDto.toBo() = ComicBo(
    name,
    resourceURI
)