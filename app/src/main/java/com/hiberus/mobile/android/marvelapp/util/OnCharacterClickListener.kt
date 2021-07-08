package com.hiberus.mobile.android.marvelapp.util

import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo

interface OnCharacterClickListener {
    fun onCharacterClicked(character: CharacterVo)
}