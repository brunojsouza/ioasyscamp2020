package br.com.brunojsouza.ioasyscamp2020.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunojsouza.ioasyscamp2020.domain.ThreadContextProvider
import br.com.brunojsouza.ioasyscamp2020.service.Failure
import br.com.brunojsouza.ioasyscamp2020.service.Repository
import br.com.brunojsouza.ioasyscamp2020.service.Service
import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine

class MainActivityViewModel : ViewModel() {
    private val repository = Repository()

    private val _cepResponse = MutableLiveData<CEPResponse>()
    val cepResponse: LiveData<CEPResponse> = _cepResponse

    private val _cepResponseError = MutableLiveData<Failure.ServiceError>()
    val cepResponseError: LiveData<Failure.ServiceError> = _cepResponseError


    fun consultCEP(cep: String) {
        viewModelScope.launch {
             repository.getCEP(cep).either(::success, ::error)
        }
    }

    private fun success(response: CEPResponse) {
        _cepResponse.value = response
    }

    private fun error(failure: Failure.ServiceError) {
        _cepResponseError.value = failure
    }
}