package com.mapx.kosten.mosimpa.domain.common

class Constants {
    companion object{
        const val SERVER_URI = "tcp://192.168.0.83"

        const val SERVER_URI_PREFIX = "tcp://"

        const val EMPTY_STRING = ""

        const val SENSOR_O2_ID = 1
        const val SENSOR_HEART_ID = 2
        const val SENSOR_BLOOD_ID = 3
        const val SENSOR_TEMPERATURE_ID = 4

        const val SENSOR_O2_Y_MAX = 100F
        const val SENSOR_O2_Y_MIN = 0F

        const val SENSOR_HEART_Y_MAX = 3000F
        const val SENSOR_HEART_Y_MIN = 100F

        const val SENSOR_BLOOD_Y_MAX = 100F
        const val SENSOR_BLOOD_Y_MIN = 10F

        const val SENSOR_TEMPERATURE_Y_MAX = 10000F
        const val SENSOR_TEMPERATURE_Y_MIN = 0F
    }
}