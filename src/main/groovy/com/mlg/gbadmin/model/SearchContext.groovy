package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.MonthsEnum

class SearchContext {
    Integer page = 0
    Integer size = 10
    Long studentNumber
    MonthsEnum referenceMonth
}
