package com.hiberus.mobile.android.local.characters

import androidx.room.*
import com.hiberus.mobile.android.local.characters.dbo.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Transaction
    @Query("SELECT * FROM Characters ORDER BY name ASC")
    suspend fun getAllCharacters(): List<CharacterSummaryDbo>?

    @Transaction
    @Query("SELECT * FROM Characters WHERE id = :id LIMIT 1")
    suspend fun getCharacter(id: Long): CharacterSummaryDbo?

    @Query("SELECT `offset` FROM [Offset]")
    suspend fun getOffset(): Int?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharactersList(items: List<CharacterSummaryDbo>, offset: OffsetDbo) {
        items.forEach { insertCharacterSummary(it) }
        insertOffset(offset)
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterSummary(item: CharacterSummaryDbo) {
        insertCharacter(item.character)
        insertComics(item.comics)
        insertEvents(item.events)
        insertSeries(item.series)
        insertStories(item.stories)
        insertUrls(item.urls)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(item: CharacterDbo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(item: List<ComicDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvents(item: List<EventDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSeries(item: List<SerieDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStories(item: List<StoryDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrls(item: List<UrlDbo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffset(offset: OffsetDbo)

    @Delete
    suspend fun delete(item: CharacterDbo)

    @Delete
    suspend fun delete(items: List<CharacterDbo>)
}