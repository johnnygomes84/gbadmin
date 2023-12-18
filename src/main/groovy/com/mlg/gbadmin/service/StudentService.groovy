package com.mlg.gbadmin.service

import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.repository.StudentRepository
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
@TupleConstructor(includeFields = true, defaults = false)
class StudentService {

    private final StudentRepository repository

    Student createOrUpdate(Student student) {
        if (!student.studentNumber) {
            Random rnd = new Random()
            student.studentNumber = rnd.nextLong(11111, 99999)
        }
        repository.save(student)
    }

    Page<Student> findAll(SearchContext searchContext) {
        repository.findAll(PageRequest.of(searchContext.page, searchContext.size))
    }

    Student findById(String id) {
        repository.findById(id).orElse(null)
    }

    Boolean deleteById(String id) {
        repository.deleteById(id)
        true
    }

    List<Student> findByStatus(SearchContext context) {
        repository.findByStatus(context.studentStatus)
    }
}
