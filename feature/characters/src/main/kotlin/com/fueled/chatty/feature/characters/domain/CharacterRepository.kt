package com.fueled.chatty.feature.characters.domain

import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.network.data.asResult
import com.fueled.chatty.core.network.data.model.whenError
import com.fueled.chatty.core.network.data.model.whenSuccess
import com.fueled.chatty.core.ui.model.Page
import com.fueled.chatty.feature.characters.data.CharactersApi
import com.fueled.chatty.feature.characters.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CharacterRepository @Inject constructor(
    private val charactersApi: CharactersApi,
    private val dispatcherProvider: DispatcherProvider,
) {

    suspend fun getCharacters(nameQuery: String? = null): Flow<Page<Character>> = flow {
        fetchCharacters(nameQuery)
            .whenSuccess { pagedList ->
                val items = pagedList.results.map { dto -> dto.toEntity() }
                emit(Page(items))
            }.whenError { error -> throw error }
    }.flowOn(dispatcherProvider.io)

    suspend fun getCharacter(id: Long): Flow<Character> = flow {
        fetchCharacter(id)
            .whenSuccess { pagedResults ->
                pagedResults.results.firstOrNull()?.let {
                    emit(it.toEntity())
                }
            }
            .whenError { error -> throw error }
    }.flowOn(dispatcherProvider.io)

    private suspend fun fetchCharacters(nameQuery: String?) =
        charactersApi.getCharacters(nameQuery).asResult()

    private suspend fun fetchCharacter(id: Long) = charactersApi.getCharacter(id).asResult()
}
