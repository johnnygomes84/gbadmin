package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.MonthsEnum
import groovy.transform.Canonical
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.core.mapping.FieldType

import java.time.LocalDateTime

@Canonical
@Document
class Tuition implements Serializable {

    private static final long serialVersionUID = 1L

    @Id
    String id
    Long studentNumber
    LocalDateTime transactionDate = LocalDateTime.now()
    @Field(targetType = FieldType.DECIMAL128)
    BigDecimal amount
    MonthsEnum referenceMonth
}
