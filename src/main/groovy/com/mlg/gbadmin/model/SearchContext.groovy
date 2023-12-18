package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.MonthsEnum
import com.mlg.gbadmin.enums.StatusEnum

class SearchContext {
    Integer page = 0
    Integer size = 10
    Long studentNumber
    MonthsEnum referenceMonth
    StatusEnum studentStatus
}
