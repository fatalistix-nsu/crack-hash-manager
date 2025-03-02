package com.github.fatalistix

import com.github.fatalistix.routes.external.StartCrackRequest
import com.github.fatalistix.routes.external.registerCrackRoutes
import com.github.fatalistix.routes.internal.registerWorkerRoutes
import com.github.fatalistix.validators.validateRequest
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRouting() {
    install(RequestValidation) {
        validate<StartCrackRequest> { request -> validateRequest(request).toValidationResult() }
    }

    registerCrackRoutes()
    registerWorkerRoutes()
}

fun <T> Result<T>.toValidationResult(): ValidationResult {
    return if (this.isSuccess) {
        ValidationResult.Valid
    } else {
        val message = this.exceptionOrNull()!!.message ?: ""
        ValidationResult.Invalid(message)
    }
}
