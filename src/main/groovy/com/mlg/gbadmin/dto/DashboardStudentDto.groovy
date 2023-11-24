package com.mlg.gbadmin.dto

import groovy.transform.Canonical

@Canonical
class DashboardStudentDto {
    Long studentTotal
    Long studentActive
    Long studentInactive
    Long studentCanceled
    Long studentAdult
    Long studentKids
}
