package br.com.brunojsouza.ioasyscamp2020.service.model.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CEPResponse(
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("cep")
    val cep: String,
    @SerializedName("complemento")
    val complemento: String,
    @SerializedName("gia")
    val gia: String?,
    @SerializedName("ibge")
    val ibge: String,
    @SerializedName("localidade")
    val localidade: String,
    @SerializedName("logradouro")
    val logradouro: String,
    @SerializedName("uf")
    val uf: String,
    @SerializedName("unidade")
    val unidade: String?
): Serializable