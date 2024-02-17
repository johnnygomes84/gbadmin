package com.mlg.gbadmin.service

import com.mlg.gbadmin.dto.dashboard.DashResponseDto
import com.mlg.gbadmin.dto.dashboard.DashboardStudentDto
import com.mlg.gbadmin.dto.dashboard.DashboardTuitionDto
import com.mlg.gbadmin.enums.ClassTypeEnum
import com.mlg.gbadmin.enums.MonthsEnum
import com.mlg.gbadmin.enums.StatusEnum
import com.mlg.gbadmin.model.Student
import com.mlg.gbadmin.repository.StudentRepository
import com.mlg.gbadmin.repository.TuitionRepository
import groovy.transform.TupleConstructor
import org.springframework.stereotype.Service

import static com.mlg.gbadmin.utils.CalendarUtils.*

@Service
@TupleConstructor(includeFields = true, defaults = false)
class DashboardService {

    private final StudentRepository studentRepository
    private final TuitionRepository tuitionRepository

    DashResponseDto getFullDash() {
        List<Student> studentFullList = studentRepository.findAll()
        DashResponseDto dashResponse = new DashResponseDto()
        dashResponse.dashStudent = getStudentDash(studentFullList)
        dashResponse.dashTuition = getDashTuition(studentFullList.findAll { it.status == StatusEnum.ACTIVE }.studentNumber)
        dashResponse
    }

    private DashboardStudentDto getStudentDash(List<Student> studentFullList) {
        DashboardStudentDto dashStudent = new DashboardStudentDto()
        dashStudent.studentTotal = studentFullList.size()
        dashStudent.studentActive = studentFullList.count { it.status == StatusEnum.ACTIVE }
        dashStudent.studentInactive = studentFullList.count { it.status == StatusEnum.INACTIVE }
        dashStudent.studentCanceled = studentFullList.count { it.status == StatusEnum.CANCELED }
        dashStudent.studentMini = studentFullList.count { it.classType == ClassTypeEnum.MINI }
        dashStudent.studentPc1 = studentFullList.count { it.classType == ClassTypeEnum.PC1 }
        dashStudent.studentPc2 = studentFullList.count { it.classType == ClassTypeEnum.PC2 }
        dashStudent.studentJuniors = studentFullList.count { it.classType == ClassTypeEnum.JUNIORES }
        dashStudent.studentGB1 = studentFullList.count { it.classType == ClassTypeEnum.GB1 }
        dashStudent.studentGB2 = studentFullList.count { it.classType == ClassTypeEnum.GB2 }
        dashStudent
    }

    private DashboardTuitionDto getDashTuition(List<Long> activeStudents) {
        DashboardTuitionDto dashTuition = new DashboardTuitionDto()
        Long tuitionPaid = tuitionRepository.countByReferenceMonth(MonthsEnum.valueOf(getCurrentMonth()))
        dashTuition.tuitionPaid = tuitionPaid
        dashTuition.tuitionOpen = activeStudents.size() - tuitionPaid
        dashTuition.tuitionOpenLonger = tuitionRepository
                .countByReferenceMonthNotAndStudentNumberNotIn(MonthsEnum.valueOf(getPreviousMonth()), activeStudents)
        dashTuition
    }

}
