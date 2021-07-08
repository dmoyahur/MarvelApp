package com.hiberus.mobile.android.marvelapp.characters.mapper

import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.model.characters.bo.CharacterBo

internal fun List<CharacterBo>.toVo() = this.map { it.toVo() }

private fun CharacterBo.toVo(): CharacterVo =
    CharacterVo(
        id,
        name,
        description,
        thumbnail.path,
        thumbnail.extension
    )