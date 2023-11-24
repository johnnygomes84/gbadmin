package com.mlg.gbadmin.repository

import com.mlg.gbadmin.model.Student
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findByStudentNumber(Long studentNumber)
}