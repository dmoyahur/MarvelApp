package com.hiberus.mobile.android.remote.characters.mapper

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.remote.characters.dto.CharacterDataWrapperDto
import com.hiberus.mobile.android.remote.characters.dto.CharacterDto

internal fun CharacterDataWrapperDto.toBo() = data.results.map { it.toBo() }

private fun CharacterDto.toBo() = CharacterBo(
    id,
    name,
    description,
    modified,
    resourceURI,
    thumbnail.toBo(),
    urls.toBo(),
    comics.toBo(),
    stories.toBo(),
    events.toBo(),
    series.toBo()
)
