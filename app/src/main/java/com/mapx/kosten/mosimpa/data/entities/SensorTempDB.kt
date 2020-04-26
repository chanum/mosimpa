package com.mapx.kosten.mosimpa.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.mapx.kosten.mosimpa.data.db.Constants.Companion.SENSOR_TEMP_TABLE

@Entity(tableName = SENSOR_TEMP_TABLE,
    foreignKeys = [
        ForeignKey(
            entity = PatientDB::class,
            parentColumns = ["id"],
            childColumns = ["patientId"],
            onDelete = ForeignKey.CASCADE)
    ]
)
data class SensorTempDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val patientId: Long = -1,
    val time: Long = -1,
    val temp: Float = -1F
)