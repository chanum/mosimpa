package com.mapx.kosten.mosimpa.domain.data

import com.mapx.kosten.mosimpa.domain.entites.*
import kotlinx.coroutines.flow.Flow

interface SensorsRepository {
    suspend fun connectMqtt(mac: String): String
    fun subscribeId(internment: InternmentEntity)
    fun unSubscribeId(internment: InternmentEntity)
    suspend fun subscribeToAll(): String
    fun updateInternments()
    fun getO2Data(): Flow<SensorO2Entity?>
    fun getBloodData(): Flow<SensorBloodEntity?>
    fun getHeartData(): Flow<SensorHeartEntity?>
    fun getTempData(): Flow<SensorTempEntity?>
}