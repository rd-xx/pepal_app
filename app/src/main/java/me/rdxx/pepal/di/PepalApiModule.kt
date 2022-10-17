package me.rdxx.pepal.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.rdxx.pepal.data.api.ApiConstants
import me.rdxx.pepal.data.api.PepalApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PepalApiModule {
    @Provides
    @Singleton
    fun provideApi(builder: Retrofit.Builder): PepalApi {
        return builder.build().create(PepalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(ApiConstants.BASE_URL)
    }
}