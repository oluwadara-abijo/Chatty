package com.fueled.chatty.core.network.di

import com.fueled.chatty.core.network.ChatsApi
import com.fueled.chatty.core.network.Parser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {
    @Provides
    fun providesChatsApi(parser: Parser) = ChatsApi(parser)
}
