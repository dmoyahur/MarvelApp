package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.bo.ImageBo
import com.hiberus.mobile.android.remote.characters.dto.ImageDto

internal fun ImageDto.toBo() = ImageBo(
    extension,
    path
)