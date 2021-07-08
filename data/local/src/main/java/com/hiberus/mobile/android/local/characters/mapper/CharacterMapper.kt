package com.hiberus.mobile.android.local.characters.mapper

import com.hiberus.mobile.android.local.characters.dbo.CharacterDbo
import com.hiberus.mobile.android.local.characters.dbo.CharacterSummaryDbo
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.bo.ImageBo

internal fun List<CharacterSummaryDbo>.toBo() = this.map { it.toBo() }

internal fun CharacterSummaryDbo.toBo() = CharacterBo(
    this.character.id,
    this.character.name,
    this.character.description,
    this.character.modified,
    this.character.resourceURI,
    getImageBo(this.character),
    urls.toBo(),
    comics.toBo(),
    stories.toBo(),
    events.toBo(),
    series.toBo()
)

internal fun List<CharacterBo>.toDbo() = this.map { it.toDbo() }

private fun CharacterBo.toDbo() = CharacterSummaryDbo(
    this.toCharacterDbo(),
    urls.toDbo(id),
    comics.toDbo(id),
    stories.toDbo(id),
    events.toDbo(id),
    series.toDbo(id)
)

private fun CharacterBo.toCharacterDbo() = CharacterDbo(
    id,
    name,
    description,
    modified,
    resourceURI,
    thumbnail.extension,
    thumbnail.path
)

private fun getImageBo(character: CharacterDbo) =
    ImageBo(
        character.imageExtension,
        character.imagePath
    )
