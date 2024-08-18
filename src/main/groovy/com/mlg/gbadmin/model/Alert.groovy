package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.AlertStatus
import com.mlg.gbadmin.enums.AlertType
import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.LocalDate

@Canonical
@Document
class Alert {
    @Id
    String id
    Long studentNumber
    AlertType type
    String message
    LocalDate openDate = LocalDate.now()
    LocalDate statusDate
    AlertStatus status
}
