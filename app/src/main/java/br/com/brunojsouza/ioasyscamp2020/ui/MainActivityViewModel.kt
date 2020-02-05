package br.com.brunojsouza.ioasyscamp2020.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.brunojsouza.ioasyscamp2020.service.Service
import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private val _cepResponse = MutableLiveData<CEPResponse>()
    val cepResponse: LiveData<CEPResponse> = _cepResponse


    fun consultCEP(cep: String) {
        Service.retrofit.consultCEP(cep)
            .enqueue(object : Callback<CEPResponse> {
                override fun onFailure(call: Call<CEPResponse>, t: Throwable) {
                    Log.d("Erro", t.toString())
                }

                override fun onResponse(call: Call<CEPResponse>, response: Response<CEPResponse>) {
                    Log.d("Sucesso", response.body().toString())
                    response.body()?.let { endereco ->
                        _cepResponse.value = endereco
                    }
                }
            })
    }
}