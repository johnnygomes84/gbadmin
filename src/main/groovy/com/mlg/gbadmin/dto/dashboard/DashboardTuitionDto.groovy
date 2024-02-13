package com.mlg.gbadmin.dto.dashboard

import groovy.transform.Canonical

@Canonical
class DashboardTuitionDto {
    Long tuitionPaid
    Long tuitionOpen
    Long tuitionOpenLonger
}
