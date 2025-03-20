package dev.donmanuel.cartoonapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.cartoonapp.data.remote.CartoonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * NetworkModule is a Dagger module that provides network-related dependencies.
 * It includes the Retrofit instance and the CartoonApi interface.
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.sampleapis.com/cartoons/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCartoonApi(retrofit: Retrofit): CartoonApi {
        return retrofit.create(CartoonApi::class.java)
    }

}