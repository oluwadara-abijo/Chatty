package com.fueled.chatty.di

import android.app.Application
import android.content.Context
import com.fueled.chatty.StringProviderImpl
import com.fueled.chatty.core.common.StringProvider
import com.fueled.chatty.core.ui.util.ErrorHandler
import com.fueled.chatty.core.ui.util.ErrorHandlerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideApplicationContext(application: Application): Context =
        application.applicationContext

    @Provides
    fun providesResourceProvider(resourceProvider: StringProviderImpl): StringProvider =
        resourceProvider

    @Provides
    fun provideErrorHandler(errorHandler: ErrorHandlerImpl): ErrorHandler = errorHandler
}
