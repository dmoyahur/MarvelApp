package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.SerieDbo
import com.hiberus.mobile.android.local.characters.dbo.StoryDbo
import com.hiberus.mobile.android.model.characters.bo.SerieBo
import com.hiberus.mobile.android.model.characters.bo.StoryBo

internal fun List<SerieDbo>.toBo() = this.map { it.toBo() }

private fun SerieDbo.toBo() = SerieBo(
    name,
    resourceURI
)

internal fun List<SerieBo>.toDbo(characterId: Int) = this.map { it.toDbo(characterId) }

private fun SerieBo.toDbo(characterId: Int) = SerieDbo(
    characterId,
    name,
    resourceURI
)
