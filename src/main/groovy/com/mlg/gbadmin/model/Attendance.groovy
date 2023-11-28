package com.mlg.gbadmin.model

import groovy.transform.Canonical
import groovyjarjarantlr4.v4.runtime.misc.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.lang.NonNull

import java.time.LocalDate

@Canonical
@Document
class Attendance {
    @Id
    String id
    @NonNull
    Long studentNumber
    @NotNull
    LocalDate attendanceDate
}
