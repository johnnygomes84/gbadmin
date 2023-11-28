package com.mlg.gbadmin.controller

import com.mlg.gbadmin.model.Attendance
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.service.AttendanceService
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/api/attendance")
class AttendanceController {

    private final AttendanceService service

    @GetMapping("/all")
    ResponseEntity<Page<Attendance>> findAll(SearchContext context) {
        ResponseEntity.ok(service.findAll(context))
    }

    @PostMapping("/new")
    ResponseEntity<Attendance> createNew(@RequestBody Attendance attendance) {
        ResponseEntity.ok(service.createOrUpdate(attendance))
    }

    @PutMapping("/update")
    ResponseEntity<Attendance> updateAttendance(@RequestBody Attendance attendance) {
        ResponseEntity.ok(service.createOrUpdate(attendance))
    }

    @PostMapping("/new/bulk")
    ResponseEntity<List<Attendance>> createNewBulk(@RequestBody List<Attendance> attendanceList) {
        ResponseEntity.ok(service.createBulkAttendance(attendanceList))
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteById(@PathVariable String id) {
        ResponseEntity.ok(service.deleteAttendance(id))
    }

}
