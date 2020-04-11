package com.mapx.kosten.mosimpa.presentation.fragments.settings

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mapx.kosten.mosimpa.domain.interactors.patient.GetPatientsUseCase
import com.mapx.kosten.mosimpa.domain.interactors.settings.GetBrokerConfigUseCase
import com.mapx.kosten.mosimpa.domain.interactors.settings.SetBrokerConfigUseCase
import com.mapx.kosten.mosimpa.presentation.common.BaseViewModel
import com.mapx.kosten.mosimpa.presentation.common.SingleLiveEvent
import kotlinx.coroutines.launch

class SettingsViewModel (
    private val getPatientsUseCase: GetPatientsUseCase,
    private val getBrokerConfigUseCase: GetBrokerConfigUseCase,
    private val setBrokerConfigUseCase: SetBrokerConfigUseCase
): BaseViewModel() {

    var viewState: MutableLiveData<SettingsViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> = SingleLiveEvent()

    init {
        val viewState = SettingsViewState()
        this.viewState.value = viewState
    }

    fun loadPatients() {
        addDisposable(getPatientsUseCase.observable()
            .subscribe({ nodes ->
                val newViewState = viewState.value?.copy(
                    isEmpty = nodes.isEmpty(),
                    isLoading = false,
                    patients = nodes)
                viewState.value = newViewState
                errorState.value = null
                Log.i(javaClass.simpleName, "Rcv Ok")
            } , {
                viewState.value = viewState.value?.copy(isLoading = false, isEmpty = false)
                errorState.value = it
                Log.i(javaClass.simpleName, "Rcv Error")
            })
        )
    }

    fun getBrokerIp(): String {
        return getBrokerConfigUseCase.invoke()
    }


    fun setBrokerIp(ip: String) {
        viewModelScope.launch {
            setBrokerConfigUseCase.invoke(ip)
        }
    }
}
