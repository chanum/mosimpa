package com.mapx.kosten.mosimpa.di

import com.mapx.kosten.mosimpa.di.modules.AppModule
import com.mapx.kosten.mosimpa.di.modules.DataModule
import com.mapx.kosten.mosimpa.di.modules.NetworkModule
import com.mapx.kosten.mosimpa.di.modules.patients.PatientsModule
import com.mapx.kosten.mosimpa.di.modules.patients.PatientsSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AppModule::class),
    (NetworkModule::class),
    (DataModule::class)
])

interface MainComponent {
    fun plus(loginModule: PatientsModule): PatientsSubComponent
}