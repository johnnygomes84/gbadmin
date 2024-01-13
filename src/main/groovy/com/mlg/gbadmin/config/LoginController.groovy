package com.mlg.gbadmin.config

import com.mlg.gbadmin.dto.user.AuthDto
import com.mlg.gbadmin.dto.user.AuthResponseDto
import com.mlg.gbadmin.service.UserService
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/auth")
@Slf4j
@CrossOrigin("*")
class LoginController {

    private final UserService service
    private final AuthenticationManager authenticationManager

    @PostMapping("/login")
    ResponseEntity<AuthResponseDto> login(@RequestBody @Valid AuthDto data){
        log.info("Request login")
        def usernamePassword = new UsernamePasswordAuthenticationToken(data.email, data.password)
        def auth = authenticationManager.authenticate(usernamePassword)
        return ResponseEntity.ok(service.getLoginCredentials(auth))
    }
}
