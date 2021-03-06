package com.mapx.kosten.mosimpa.domain.interactors.sensor

import com.mapx.kosten.mosimpa.domain.data.SensorsRepository

class ConnectClientMqttUseCase(
    private val sensorsRepository: SensorsRepository
) {
    suspend fun invoke() = sensorsRepository.connectMqtt()
}