package com.mlg.gbadmin.dto.email

import groovy.transform.Canonical

@Canonical
class EmailRequestDto {
    String emailTo
    String subject
    String message
    boolean isHtml
}
