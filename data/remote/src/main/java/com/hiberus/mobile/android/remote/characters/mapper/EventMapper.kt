package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.bo.EventBo
import com.hiberus.mobile.android.remote.characters.dto.EventListDto
import com.hiberus.mobile.android.remote.characters.dto.EventSummaryDto

internal fun EventListDto.toBo() = this.items.map { it.toBo() }

private fun EventSummaryDto.toBo() = EventBo(
    name,
    resourceURI
)