package br.com.brunojsouza.ioasyscamp2020.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunojsouza.ioasyscamp2020.domain.Either
import br.com.brunojsouza.ioasyscamp2020.domain.Success
import br.com.brunojsouza.ioasyscamp2020.domain.ThreadContextProvider
import br.com.brunojsouza.ioasyscamp2020.service.Service
import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {
    private val contextProvider = ThreadContextProvider()

    private val _cepResponse = MutableLiveData<CEPResponse>()
    val cepResponse: LiveData<CEPResponse> = _cepResponse


//    fun consultCEP(cep: String) {
//        Service.retrofit.consultCEP(cep)
//            .enqueue(object : Callback<CEPResponse> {
//                override fun onFailure(call: Call<CEPResponse>, t: Throwable) {
//                    Log.d("Erro", t.toString())
//                }
//
//                override fun onResponse(call: Call<CEPResponse>, response: Response<CEPResponse>) {
//                    Log.d("Sucesso", response.body().toString())
//                    response.body()?.let { endereco ->
//                        _cepResponse.value = endereco
//                    }
//                }
//            })
//    }

    fun consultCEP(cep: String) {
        CoroutineScope(contextProvider.io).launch {
            Service.retrofit.consultCEP(cep).either({ success ->{
                _cepResponse.value = success
            } }){

            }
        }
    }
}