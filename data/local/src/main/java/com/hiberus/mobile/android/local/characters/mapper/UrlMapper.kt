package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.UrlDbo
import com.hiberus.mobile.android.model.characters.UrlBo

internal fun List<UrlDbo>.toBo() = this.map { it.toBo() }

private fun UrlDbo.toBo() = UrlBo(
    type,
    url
)

internal fun List<UrlBo>.toDbo(characterId: Int) = this.map { it.toDbo(characterId) }

private fun UrlBo.toDbo(characterId: Int) = UrlDbo(
    characterId,
    type,
    url
)