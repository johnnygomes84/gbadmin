package com.mlg.gbadmin.controller

import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.service.StudentService
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
@RequestMapping("/api/student")
class StudentController {

    private final StudentService service

    @PostMapping("/new")
    ResponseEntity<Student> createNew(@RequestBody Student student) {
        ResponseEntity.ok(service.createOrUpdate(student))
    }

    @PutMapping("/update")
    ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        ResponseEntity.ok(service.createOrUpdate(student))
    }

    @GetMapping("/all")
    ResponseEntity<Page<Student>> findAll(SearchContext searchContext) {
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @GetMapping("/all/actives")
    ResponseEntity<List<Student>> findAllByStatus(SearchContext context) {
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteStudent(@PathVariable String id) {
        ResponseEntity.ok(service.deleteById(id))
    }

}
