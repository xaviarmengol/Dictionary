package com.plcoding.dictionary.feature_dictionary.data.repository

import com.plcoding.dictionary.core.util.Resource
import com.plcoding.dictionary.feature_dictionary.data.local.WordInfoDao
import com.plcoding.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.plcoding.dictionary.feature_dictionary.data.remote.dto.WordInfosDto
import com.plcoding.dictionary.feature_dictionary.domain.model.WordInfo
import com.plcoding.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val api: DictionaryApi,
    private val dao: WordInfoDao
) : WordInfoRepository {


    override fun getWordInfo(word: String, lang: String): Flow<Resource<List<WordInfo>>>  = flow {
        // All data should come from DB. Single source of truth

        var wordSearched = word

        emit(Resource.Loading())

        val wordInfos = dao.getWordInfosWithLang(wordSearched, lang).map {it.toWordInfo()}
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word = wordSearched, lang = lang)

            // API returns similar words and we are only interested in the original one
            val remoteWordInfosFiltered : MutableList<WordInfosDto> =
                remoteWordInfos.filter { it -> it.word == wordSearched }.toMutableList()

            // If the list is empty after filtering, use the first definition as the word
            if (remoteWordInfosFiltered.isEmpty()) {
                remoteWordInfosFiltered.add(remoteWordInfos[0])
                wordSearched = remoteWordInfosFiltered[0].word
            }

            dao.deleteWordInfosWithLang(remoteWordInfosFiltered.map{it.word}, lang)
            dao.insertWordInfos(remoteWordInfosFiltered.map{it.toWordInfosEntity(lang = lang)})

        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Word not found",
                data = wordInfos // From DB
            ))
        } catch (e: IOException) {
            emit(Resource.Error(
                message = "Could't reach the server",
                data = wordInfos // From DB
            ))
        }

        val newWordInfos = dao.getWordInfosWithLang(wordSearched, lang).map{it.toWordInfo()}
        emit (Resource.Success(newWordInfos))

    }

}