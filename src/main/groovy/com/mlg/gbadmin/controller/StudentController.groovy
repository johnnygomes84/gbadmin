package com.mlg.gbadmin.controller

import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.service.StudentService
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/api/student")
@CrossOrigin("*")
class StudentController {

    private final StudentService service

    @PostMapping("/new")
    ResponseEntity<Student> createNew(@RequestBody Student student) {
        log.info("Creating new student")
        ResponseEntity.ok(service.createOrUpdate(student))
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        log.info("Updating student: {}", student.name)
        ResponseEntity.ok(service.createOrUpdate(student))
    }

    @GetMapping("/all")
    ResponseEntity<Page<Student>> findAll(SearchContext searchContext) {
        log.info("Getting all students")
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @GetMapping("/{id}")
    ResponseEntity<Student> findById(@PathVariable String id) {
        log.info("Getting student by id")
        ResponseEntity.ok(service.findById(id))
    }

    @GetMapping("/studentNumber/{studentNumber}")
    ResponseEntity<Student> findByStudentNumber(@PathVariable Long studentNumber) {
        log.info("Getting student by id")
        ResponseEntity.ok(service.findByStudentNumber(studentNumber))
    }

    @GetMapping("/all/actives")
    ResponseEntity<List<Student>> findAllByStatus(SearchContext context) {
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteStudent(@PathVariable String id) {
        log.info("Deleting student: {}", id)
        ResponseEntity.ok(service.deleteById(id))
    }

}
