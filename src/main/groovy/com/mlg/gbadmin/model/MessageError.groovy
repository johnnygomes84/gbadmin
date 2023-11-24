package com.mlg.gbadmin.model

import groovy.transform.Canonical
import org.springframework.http.HttpStatus

@Canonical
class MessageError {
    HttpStatus code
    String message
}
