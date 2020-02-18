package br.com.brunojsouza.ioasyscamp2020.service

import br.com.brunojsouza.ioasyscamp2020.domain.Either
import br.com.brunojsouza.ioasyscamp2020.domain.Error
import br.com.brunojsouza.ioasyscamp2020.domain.Success
import br.com.brunojsouza.ioasyscamp2020.service.model.response.CEPResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Repository {
    private val netWorking = NetWorking()

    suspend fun getCEP(cep: String): Either<CEPResponse, Failure.ServiceError> =
        withContext(Dispatchers.IO) {
            try {
                Success(netWorking.getCEP(cep))
            } catch (e: Throwable) {
                Error(Failure.ServiceError(e.message))
            }
        }
}
