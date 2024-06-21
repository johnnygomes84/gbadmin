package com.mlg.gbadmin.service

import com.mlg.gbadmin.dto.email.EmailRequestDto
import com.mlg.gbadmin.exception.EntityNotFoundException
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.repository.StudentRepository
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

import static com.mlg.gbadmin.utils.QrCodeUtils.generateQRCodeImageAsByteArray

@Service
@TupleConstructor(includeFields = true, defaults = false)
class StudentService {

    private final StudentRepository repository
    private final EmailService emailService

    Student createOrUpdate(Student student) {
        if (!student.studentNumber) {
            Random rnd = new Random()
            student.studentNumber = rnd.nextLong(11111, 99999)
            sendStudentCard(student.email, student.studentNumber)
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

    Student findByStudentNumber(Long studentNumber) {
        repository.findByStudentNumber(studentNumber)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"))
    }

    private void sendStudentCard(String email, Long studentNumber) {
        EmailRequestDto req = new EmailRequestDto(
                emailTo: email,
                subject: "GB Student Card",
                message: studentNumber,
                isHtml: true
        )
        emailService.sendMail(req)
    }
}
