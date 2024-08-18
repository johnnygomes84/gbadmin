package com.mlg.gbadmin.repository

import com.mlg.gbadmin.enums.AlertStatus
import com.mlg.gbadmin.model.Alert
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AlertRepository extends MongoRepository<Alert, String> {

    List<Alert> findAllByStatus(AlertStatus status)
    List<Alert> findAllByStudentNumber(Long studentNumber)
    void deleteAllByStatus(AlertStatus status)
}