package com.mlg.gbadmin.controller

import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.model.Tuition
import com.mlg.gbadmin.service.TuitionService
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/api/tuition")
@Slf4j
@CrossOrigin("*")
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
        log.info("Getting all tuition")
        ResponseEntity.ok(service.findAll(searchContext))
    }

    @GetMapping("/{id}")
    ResponseEntity<Page<Student>> findById(@PathVariable String id) {
        ResponseEntity.ok(service.findById(id))
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

    @GetMapping("/dashboard")
    ResponseEntity<Page<Tuition>> getDashboardTuition() {
        ResponseEntity.ok(service.getDashboardTuition())
    }

    @GetMapping("/student/open")
    ResponseEntity<Page<Student>> getStudentOpenTuition(SearchContext searchContext) {
        ResponseEntity.ok(service.getStudentOpenTuition(searchContext))
    }


}
