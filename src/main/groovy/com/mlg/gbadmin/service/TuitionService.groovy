package com.mlg.gbadmin.service

import com.mlg.gbadmin.enums.MonthsEnum
import com.mlg.gbadmin.enums.StatusEnum
import com.mlg.gbadmin.exception.EntityNotFoundException
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.model.Tuition
import com.mlg.gbadmin.repository.StudentRepository
import com.mlg.gbadmin.repository.TuitionRepository
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

import static com.mlg.gbadmin.utils.CalendarUtils.getCurrentMonth

@Service
@TupleConstructor(includeFields = true, defaults = false)
class TuitionService {

    private final TuitionRepository repository
    private final StudentRepository studentRepository

    Tuition createOrUpdate(Tuition tuition) {
        Student student = studentRepository.findByStudentNumber(tuition.studentNumber)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tuition not created, Student not found for studentNumber: ${tuition.studentNumber}"))
        repository.save(tuition)
    }

    Page<Tuition> findAll(SearchContext searchContext) {
        repository.findAll(PageRequest.of(searchContext.page, searchContext.size))
    }

    Tuition findById(String id) {
        repository.findById(id).orElse(null)
    }

    Boolean deleteById(String id) {
        repository.deleteById(id)
        true
    }

    Page<Tuition> findByStudentNumber(SearchContext context) {
        repository.findByStudentNumber(PageRequest.of(context.page, context.size), context.studentNumber)
    }

    Page<Tuition> findByReferenceMonth(SearchContext context) {
        repository.findByReferenceMonth(PageRequest.of(context.page, context.size), context.referenceMonth)
    }

    Page<Student> getStudentOpenTuition(SearchContext context) {
        List<Long> studentNumberPaid = repository.findByReferenceMonth(MonthsEnum.valueOf(getCurrentMonth())).studentNumber
        studentRepository.findByStatusAndStudentNumberNotIn(PageRequest.of(context.page, context.size),
                                                                             StatusEnum.ACTIVE, studentNumberPaid)
    }
}
