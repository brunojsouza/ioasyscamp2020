package br.com.brunojsouza.ioasyscamp2020.service

sealed class Failure {
    data class ServiceError(
        private val messageInternal: String? = null,
        val httpCode: Int? = null,
        val errorCode: String? = null
    ) : Failure() {
        override var message = messageInternal?.takeIf { it.isNotEmpty() } ?: "Houve um erro desconhecido."
    }

    open val message: String = ""
}

data class NetworkingResponse<T>(val data: T, val headers: MutableMap<String, MutableList<String>>? = mutableMapOf())