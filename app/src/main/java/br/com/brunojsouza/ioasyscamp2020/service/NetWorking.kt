package br.com.brunojsouza.ioasyscamp2020.service

import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse

class NetWorking {
    suspend fun getCEP(cep: String): CEPResponse = try {
        Service.retrofit.consultCEP(cep)
    } catch (e: Throwable) {
        throw (e)
    }
}