package com.mlg.gbadmin.repository

import com.mlg.gbadmin.enums.StatusEnum
import com.mlg.gbadmin.model.Student
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByStudentNumber(Long studentNumber)

    Page<Student> findByStatusAndStudentNumberNotIn(PageRequest pageRequest, StatusEnum status, List<Long> studentNumber)

    Long countByStatus(StatusEnum status)

    List<Student> findByStudentNumberIn(Set<Long> studentNumberList)
}