package dev.donmanuel.cartoonapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.cartoonapp.data.repository.CartoonRepositoryImpl
import dev.donmanuel.cartoonapp.domain.repository.CartoonRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCartoonRepository(
        impl: CartoonRepositoryImpl
    ): CartoonRepository
}