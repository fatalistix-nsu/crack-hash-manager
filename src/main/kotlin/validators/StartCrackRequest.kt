package com.github.fatalistix.validators

import com.github.fatalistix.routes.external.StartCrackRequest

fun validateRequest(request: StartCrackRequest) = runCatching {
    if (request.maxLength <= 0UL) {
        throw IllegalArgumentException("max length must be greater than 0")
    }

    val md5Regex = Regex("^[a-fA-F0-9]{32}$")
    if (!md5Regex.matches(request.hash)) {
        throw IllegalArgumentException("received string is not md5 hash")
    }
}