package com.mlg.gbadmin.repository

import com.mlg.gbadmin.model.Attendance
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AttendanceRepository extends MongoRepository<Attendance, String> {

}