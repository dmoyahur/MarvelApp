package com.hiberus.mobile.android.marvelapp.util

import com.hiberus.mobile.android.marvelapp.characters.vo.CharacterVo
import com.hiberus.mobile.android.model.characters.CharacterBo
import com.hiberus.mobile.android.model.characters.ImageBo
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object DataFactory {

    private const val DEFAULT_ID = 0
    private const val EMPTY_STRING = ""

    fun makeCharacterBoList(size: Int): List<CharacterBo> {
        var charactersList: MutableList<CharacterBo> = mutableListOf()
        repeat(size) {
            charactersList.add(makeCharacterBo(it))
        }
        return charactersList
    }

    fun makeCharacterBo(id: Int) =
        CharacterBo(
            id,
            "Character_$id",
            "Description",
            getDate(),
            EMPTY_STRING,
            ImageBo(EMPTY_STRING, EMPTY_STRING),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList(),
            emptyList()
        )

    fun makeCharacterVo(id: Int = DEFAULT_ID) = CharacterVo(
        id,
        "Character_$id",
        "Description",
        EMPTY_STRING,
        EMPTY_STRING,
        emptyList()
    )

    fun getDate(): Date = Date.from(
        LocalDate
            .of(2021, 7, 15)
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
    )
}