package com.mapx.kosten.mosimpa.domain.interactors.sensor

import com.mapx.kosten.mosimpa.domain.data.SensorsRepository

class GetSensorBloodDataUseCase (
    private val sensorsRepository: SensorsRepository
) {
    fun invoke() = sensorsRepository.getBloodData()
}