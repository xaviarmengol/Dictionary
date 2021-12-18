package com.plcoding.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.plcoding.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(infos: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN (:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word IS :word")
    suspend fun getWordInfos(word: String): List<WordInfoEntity>

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getLikeWordInfos(word: String): List<WordInfoEntity>

    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfosWithLang(infos: List<WordInfoEntity>)

     */


    @Query("SELECT * FROM wordinfoentity WHERE word IS :word AND lang IS :lang")
    suspend fun getWordInfosWithLang(word: String, lang: String): List<WordInfoEntity>

    @Query("DELETE FROM wordinfoentity WHERE word IN (:words) AND lang IS :lang")
    suspend fun deleteWordInfosWithLang(words: List<String>, lang: String)

}