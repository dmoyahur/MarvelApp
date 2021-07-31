package com.hiberus.mobile.android.marvelapp.characters.mapper

import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.model.characters.CharacterBo

internal fun List<CharacterBo>.toVo() = this.map { it.toVo() }

internal fun CharacterBo.toVo(): CharacterVo =
    CharacterVo(
        id,
        name,
        description,
        thumbnail.path,
        thumbnail.extension,
        comics.toVo()
    )