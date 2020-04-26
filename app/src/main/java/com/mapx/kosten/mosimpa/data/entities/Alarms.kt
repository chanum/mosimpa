package com.mapx.kosten.mosimpa.data.entities

data class Alarms(
    val spo2_lt: Int = 0,
    val hr_lt: Int = 0,
    val hr_gt: Int = 0,
    val bt_gt: Int = 0,
    val bp_sys_lt: Int = 0,
    val bp_sys_gt: Int = 0
)