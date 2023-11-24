package com.mlg.gbadmin.repository

import com.mlg.gbadmin.enums.MonthsEnum
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Tuition
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface TuitionRepository extends MongoRepository<Tuition, String> {

    Page<Tuition> findByStudentNumber(Pageable pageable, Long studentNumber)

    Page<Tuition> findByReferenceMonth(Pageable pageable, MonthsEnum refMonth)

    List<Tuition> findByReferenceMonth(MonthsEnum refMonth)

    Long countByReferenceMonth(MonthsEnum refMonth)
}