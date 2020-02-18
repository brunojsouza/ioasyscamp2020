package br.com.brunojsouza.ioasyscamp2020.service

import br.com.brunojsouza.ioasyscamp2020.domain.Either
import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("{cep}/json")
    suspend fun consultCEP(@Path("cep")cep: String): CEPResponse
}