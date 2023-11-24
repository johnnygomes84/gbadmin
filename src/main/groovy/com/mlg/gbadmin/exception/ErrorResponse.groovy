package com.mlg.gbadmin.exception

class ErrorResponse {
    Long code
    String message

    static Optional<ErrorResponse> of(Long code, String message) {
        return Optional.of(new ErrorResponse(code: code, message: message))
    }
}
