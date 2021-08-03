package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.SerieBo
import com.hiberus.mobile.android.remote.characters.dto.SerieSummaryDto
import com.hiberus.mobile.android.remote.characters.dto.SeriesListDto

internal fun SeriesListDto.toBo() = this.items.map { it.toBo() }

private fun SerieSummaryDto.toBo() = SerieBo(
    name,
    resourceURI
)