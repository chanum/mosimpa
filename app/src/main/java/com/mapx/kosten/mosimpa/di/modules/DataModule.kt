package com.mapx.kosten.mosimpa.di.modules

import android.content.Context
import androidx.room.Room
import com.mapx.kosten.mosimpa.data.db.MosimpaDatabase
import com.mapx.kosten.mosimpa.data.repositories.PatientsRepositoryImpl
import com.mapx.kosten.mosimpa.data.repositories.SensorsRepositoryImpl
import com.mapx.kosten.mosimpa.data.repositories.SettingsRepositoryImpl
import com.mapx.kosten.mosimpa.domain.data.PatientsRepository
import com.mapx.kosten.mosimpa.domain.data.SensorsRepository
import com.mapx.kosten.mosimpa.domain.data.SettingsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): MosimpaDatabase {
        return Room.databaseBuilder(
            context,
            MosimpaDatabase::class.java,
            "mosimpa_db")
            .build()
    }

    @Singleton
    @Provides
    fun providePatientsRepository(
        database: MosimpaDatabase
    ): PatientsRepository {
        return PatientsRepositoryImpl(database)
    }

    @Singleton
    @Provides
    fun provideSensorsRepository(
        context: Context,
        database: MosimpaDatabase
    ): SensorsRepository {
        return SensorsRepositoryImpl(context, database)
    }

    @Singleton
    @Provides
    fun provideSettingsRepository(
        context: Context
    ): SettingsRepository {
        return SettingsRepositoryImpl(context)
    }
}