package com.hiberus.mobile.android.remote.factory

import com.hiberus.mobile.android.model.characters.bo.CharacterBo
import com.hiberus.mobile.android.model.characters.bo.ImageBo
import com.hiberus.mobile.android.remote.characters.dto.*
import com.hiberus.mobile.android.remote.utils.getDate

object DataFactory {

    private const val EMPTY_STRING = ""
    private const val DEFAULT_ITEM_ID = 0

    //region Dtos
    fun makeCharacterDataWraperDtoResponse(count: Int): CharacterDataWrapperDto {
        return CharacterDataWrapperDto(
            EMPTY_STRING,
            EMPTY_STRING,
            EMPTY_STRING,
            EMPTY_STRING,
            makeCharacterDataContainerDtoResponse(count),
            EMPTY_STRING,
            EMPTY_STRING
        )
    }

    fun makeCharacterDataContainerDtoResponse(count: Int): CharacterDataContainerDto {
        return CharacterDataContainerDto(
            EMPTY_STRING,
            EMPTY_STRING,
            EMPTY_STRING,
            makeCharacterDtoListResponse(count),
            EMPTY_STRING
        )
    }

    fun makeCharacterDtoListResponse(count: Int): List<CharacterDto> {
        val charactersList = mutableListOf<CharacterDto>()
        repeat(count) {
            charactersList.add(makeCharacterDtoResponse())
        }
        return charactersList
    }

    fun makeCharacterDtoResponse() = CharacterDto(
        DEFAULT_ITEM_ID,
        EMPTY_STRING,
        EMPTY_STRING,
        getDate(),
        EMPTY_STRING,
        emptyList(),
        ImageDto(EMPTY_STRING, EMPTY_STRING),
        ComicListDto(EMPTY_STRING, EMPTY_STRING, emptyList(), EMPTY_STRING),
        StoryListDto(EMPTY_STRING, EMPTY_STRING, emptyList(), EMPTY_STRING),
        EventListDto(EMPTY_STRING, EMPTY_STRING, emptyList(), EMPTY_STRING),
        SeriesListDto(EMPTY_STRING, EMPTY_STRING, emptyList(), EMPTY_STRING)
        )
    //endregion

    //region Bo
    fun makeCharacterBoListResponse(count: Int): List<CharacterBo> {
        val charactersList = mutableListOf<CharacterBo>()
        repeat(count) {
            charactersList.add(makeCharacterBoResponse())
        }
        return charactersList
    }

    fun makeCharacterBoResponse() = CharacterBo(
        DEFAULT_ITEM_ID,
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