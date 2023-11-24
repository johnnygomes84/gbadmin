package com.mlg.gbadmin.controller

import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.model.Tuition
import com.mlg.gbadmin.service.StudentService
import com.mlg.gbadmin.service.TuitionService
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
@RequestMapping("/api/tuition")
class TuitionController {

    private final TuitionService service

    @PostMapping("/new")
    ResponseEntity<Tuition> createNew(@RequestBody Tuition tuition) {
        ResponseEntity.ok(service.createOrUpdate(tuition))
    }

    @PutMapping("/update")
    ResponseEntity<Tuition> updateTuition(@RequestBody Tuition tuition) {
        ResponseEntity.ok(service.createOrUpdate(tuition))
    }

    @GetMapping("/all")
    ResponseEntity<Page<Tuition>> findAll(SearchContext searchContext) {
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<Boolean> deleteStudent(@PathVariable String id) {
        ResponseEntity.ok(service.deleteById(id))
    }

    @GetMapping("/number")
    ResponseEntity<Page<Tuition>> findByStudentNumber(SearchContext searchContext) {
        ResponseEntity.ok(service.findByStudentNumber(searchContext))
    }

    @GetMapping("/month")
    ResponseEntity<Page<Tuition>> findByReferenceMonth(SearchContext searchContext) {
        ResponseEntity.ok(service.findByReferenceMonth(searchContext))
    }

}
