package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.StoryBo
import com.hiberus.mobile.android.remote.characters.dto.StoryListDto
import com.hiberus.mobile.android.remote.characters.dto.StorySummaryDto

internal fun StoryListDto.toBo() = this.items.map { it.toBo() }

private fun StorySummaryDto.toBo() = StoryBo(
    name,
    resourceURI,
    type
)