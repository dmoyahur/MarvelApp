package com.hiberus.mobile.android.marvelapp.characters.mapper

import com.hiberus.mobile.android.marvelapp.characters.vo.ComicVo
import com.hiberus.mobile.android.model.characters.bo.ComicBo

internal fun List<ComicBo>.toVo() = this.map { it.toVo() }

private fun ComicBo.toVo(): ComicVo =
    ComicVo(
        resourceURI,
        name
    )