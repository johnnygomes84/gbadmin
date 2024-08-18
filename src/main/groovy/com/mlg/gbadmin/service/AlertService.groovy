package com.mlg.gbadmin.service

import com.mlg.gbadmin.model.Alert
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.repository.AlertRepository
import com.mlg.gbadmin.repository.StudentRepository
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

import java.time.LocalDate

import static com.mlg.gbadmin.enums.AlertStatus.HANDLED
import static com.mlg.gbadmin.enums.AlertStatus.NEW
import static com.mlg.gbadmin.enums.AlertStatus.OPEN
import static com.mlg.gbadmin.enums.AlertType.GRADUATION

@Slf4j
@Service
@EnableScheduling
@TupleConstructor(includeFields = true, defaults = false)
class AlertService {

    private AlertRepository repository
    private StudentRepository studentRepository

    @Scheduled(cron = "0 */3 * ? * *")
    private void scheduleAlerts() {
        log.info("Running Alerts job.")
        generateGraduationAlerts()
        removeOldAlerts()
    }

    Page<Alert> findAll(SearchContext searchContext) {
        repository.findAll(PageRequest.of(searchContext.page, searchContext.size))
    }

    Alert updateStatusAlert(Alert alert) {
        alert.statusDate = LocalDate.now()
        repository.save(alert)
    }

    private void saveAllAlerts(List<Alert> alerts) {
        repository.saveAll(alerts)
    }

    private void generateGraduationAlerts() {
        log.info("Graduation alerts start.")
        LocalDate fourMonthsAgo = LocalDate.now().minusMonths(4)
        def students = studentRepository.findAll()
        def oldAlerts = repository.findAll()

        def studentsForGraduation = students.findAll { std ->
            std.lastGraduation?.isBefore(fourMonthsAgo) && !oldAlerts.any { alert ->
                alert.studentNumber == std.studentNumber && alert.type == GRADUATION
            }
        }

        def gradAlerts = studentsForGraduation.collect { std ->
            new Alert(
                    studentNumber: std.studentNumber,
                    type: GRADUATION,
                    status: NEW,
                    message: "Last graduation for student: ${std.name} ${std.lastName} was held more than four months ago.",
                    statusDate: LocalDate.now()
            )
        }
        saveAllAlerts(gradAlerts)
    }

    private void removeOldAlerts() {
        log.info("Removing Old alerts.")
        repository.deleteAllByStatus(HANDLED)
    }
}
