package com.mlg.gbadmin.dto.dashboard

import groovy.transform.Canonical

@Canonical
class DashboardStudentDto {
    Integer studentTotal
    Integer studentActive
    Integer studentInactive
    Integer studentCanceled
    Integer studentAdult
    Integer studentKids
}
