package br.com.brunojsouza.ioasyscamp2020

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {
    @GET("{cep}/json")
    fun getConsultCEP(@Path("cep")cep: String): Call<CEPResponse>
}