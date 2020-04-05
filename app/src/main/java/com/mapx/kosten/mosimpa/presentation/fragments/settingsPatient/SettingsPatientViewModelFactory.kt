package com.mapx.kosten.mosimpa.presentation.fragments.settingsPatient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mapx.kosten.mosimpa.domain.interactors.patient.GetPatientUseCase
import com.mapx.kosten.mosimpa.domain.interactors.patient.SavePatientUseCase

class SettingsPatientViewModelFactory(
    private val savePatientUseCase: SavePatientUseCase,
    private val getPatientUseCase: GetPatientUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingsPatientViewModel(savePatientUseCase, getPatientUseCase) as T
    }
}