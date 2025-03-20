package dev.donmanuel.cartoonapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.donmanuel.cartoonapp.data.local.AppDatabase
import dev.donmanuel.cartoonapp.data.local.FavoriteCartoonDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cartoon_db"
        ).build()
    }

    @Provides
    fun provideFavoriteCartoonDao(database: AppDatabase): FavoriteCartoonDao {
        return database.favoriteCartoonDao()
    }
}