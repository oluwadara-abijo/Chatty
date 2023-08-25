package com.fueled.chatty.feature.characters.di

import com.fueled.chatty.feature.characters.data.CharactersApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal object CharacterModule {

    @Provides
    fun providesCharacterApi(retrofit: Retrofit): CharactersApi = retrofit.create()
}
