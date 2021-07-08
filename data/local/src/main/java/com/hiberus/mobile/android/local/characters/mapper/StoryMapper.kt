package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.StoryDbo
import com.hiberus.mobile.android.model.characters.bo.StoryBo

internal fun List<StoryDbo>.toBo() = this.map { it.toBo() }

private fun StoryDbo.toBo() = StoryBo(
    name,
    resourceURI,
    type
)

internal fun List<StoryBo>.toDbo(characterId: Int) = this.map { it.toDbo(characterId) }

internal fun StoryBo.toDbo(characterId: Int) = StoryDbo(
    characterId,
    name,
    resourceURI,
    type
)