package com.mlg.gbadmin.service

import ch.qos.logback.core.pattern.color.BoldBlueCompositeConverter
import com.mlg.gbadmin.exception.EntityNotFoundException
import com.mlg.gbadmin.model.Attendance
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.repository.AttendanceRepository
import com.mlg.gbadmin.repository.StudentRepository
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
@TupleConstructor(includeFields = true, defaults = false)
class AttendanceService {

    private final AttendanceRepository repository
    private final StudentRepository studentRepository

    Page<Attendance> findAll(SearchContext context) {
        repository.findAll(PageRequest.of(context.page, context.size))
    }

    List<Attendance> createBulkAttendance(List<Attendance> attendanceList) {
        checkIfStudentExists(null, attendanceList.studentNumber as Set<Long>)
        repository.saveAll(attendanceList)
    }

    Attendance createOrUpdate(Attendance attendance) {
        checkIfStudentExists(attendance.studentNumber)
        repository.save(attendance)
    }

    Boolean deleteAttendance(String id) {
        repository.deleteById(id)
    }

    private void checkIfStudentExists(Long studentNumber, Set<Long> studentNumberList = null) {
        if (studentNumberList) {
            List<Student> students = studentRepository.findByStudentNumberIn(studentNumberList)
            def userNotFound = studentNumberList - students.studentNumber
            if (userNotFound) {
                throw new EntityNotFoundException("Bulk Attendance not created, Student not found for studentNumber: ${userNotFound}")
            }
        } else {
            studentRepository.findByStudentNumber(studentNumber)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Attendance not created, Student not found for studentNumber: ${studentNumber}"))

        }

    }
}
