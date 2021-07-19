package com.hiberus.mobile.android.local.factory

import com.hiberus.mobile.android.local.characters.dbo.CharacterDbo
import com.hiberus.mobile.android.local.characters.dbo.CharacterSummaryDbo
import com.hiberus.mobile.android.local.utils.getDate
import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.bo.ImageBo

object DataFactory {

    private const val DEFAULT_ITEM_ID = 0
    private const val EMPTY_STRING = ""

    //region Dbo
    fun makeCharacterSummaryDboResponse(id: Int = DEFAULT_ITEM_ID) = CharacterSummaryDbo(
        makeCharacterDboResponse(id),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )

    fun makeCharacterDboResponse(id: Int = DEFAULT_ITEM_ID) = CharacterDbo(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        getDate(),
        EMPTY_STRING,
        EMPTY_STRING,
        EMPTY_STRING
    )
    //endregion

    //region Bo
    fun makeCharacterBoResponse(id: Int = DEFAULT_ITEM_ID) = CharacterBo(
        id,
        EMPTY_STRING,
        EMPTY_STRING,
        getDate(),
        EMPTY_STRING,
        ImageBo(EMPTY_STRING, EMPTY_STRING),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList()
    )
    //endregion
}