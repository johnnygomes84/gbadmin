package com.mlg.gbadmin.controller

import com.mlg.gbadmin.dto.dashboard.DashResponseDto
import com.mlg.gbadmin.service.DashboardService
import groovy.transform.TupleConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
class DashboardController {

    private final DashboardService dashboardService

    @GetMapping("/full")
    ResponseEntity<DashResponseDto> getFullDashboard() {
        ResponseEntity.ok(dashboardService.getFullDash())
    }
}
