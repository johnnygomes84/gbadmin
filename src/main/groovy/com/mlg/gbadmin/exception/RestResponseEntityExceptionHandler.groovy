package com.mlg.gbadmin.exception

import groovy.util.logging.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Slf4j
@ControllerAdvice
class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException)
    ResponseEntity<ErrorResponse> resourceNotFoundException(EntityNotFoundException ex) {
        log.error(ex.getMessage())
        buildResponseBody(HttpStatus.BAD_REQUEST, ex.getMessage())
    }


    private static buildResponseBody(HttpStatus httpStatus, String message) {
        new ResponseEntity<ErrorResponse>(buildResponse(httpStatus, message), httpStatus)
    }

    private static ErrorResponse buildResponse(HttpStatus httpStatus, String message) {
        [
                code: httpStatus.value(),
                message: message
        ]
    }


}
