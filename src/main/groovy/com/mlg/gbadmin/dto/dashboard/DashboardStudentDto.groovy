package com.mlg.gbadmin.dto.dashboard

import groovy.transform.Canonical

@Canonical
class DashboardStudentDto {
    Integer studentTotal
    Integer studentActive
    Integer studentInactive
    Integer studentCanceled
    Integer studentMini
    Integer studentPc1
    Integer studentPc2
    Integer studentJuniors
    Integer studentGB1
    Integer studentGB2
}
