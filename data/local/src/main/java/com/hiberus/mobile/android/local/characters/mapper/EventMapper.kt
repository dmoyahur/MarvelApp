package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.EventDbo
import com.hiberus.mobile.android.model.characters.bo.EventBo

internal fun List<EventDbo>.toBo() = this.map { it.toBo() }

private fun EventDbo.toBo() = EventBo(
    name,
    resourceURI
)

internal fun List<EventBo>.toDbo(characterId: Int) = this.map { it.toDbo(characterId) }

private fun EventBo.toDbo(characterId: Int) = EventDbo(
    characterId,
    name,
    resourceURI
)