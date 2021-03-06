package com.mapx.kosten.mosimpa.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mapx.kosten.mosimpa.data.db.Contants.Companion.SENSORS_TABLE

@Entity(tableName = SENSORS_TABLE)
data class SensorDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val name: String = "",
    var value: Float = -1F
)