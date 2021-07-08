package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.ComicDbo
import com.hiberus.mobile.android.model.characters.bo.ComicBo

internal fun List<ComicDbo>.toBo() = this.map { it.toBo() }

internal fun ComicDbo.toBo() = ComicBo(
    name,
    resourceURI
)

internal fun List<ComicBo>.toDbo(characterId: Int) = this.map { it.toDbo(characterId) }

internal fun ComicBo.toDbo(characterId: Int) = ComicDbo(
    characterId,
    name,
    resourceURI
)