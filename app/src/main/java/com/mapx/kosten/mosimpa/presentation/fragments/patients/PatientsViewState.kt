package com.mapx.kosten.mosimpa.presentation.fragments.patients

import com.mapx.kosten.mosimpa.domain.entites.PatientEntity

data class PatientsViewState(
    val isLoading: Boolean = true,
    val isEmpty: Boolean = true,
    val patients: List<PatientEntity>? = null
)