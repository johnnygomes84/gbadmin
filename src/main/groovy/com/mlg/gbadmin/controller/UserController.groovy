package com.mlg.gbadmin.controller

import com.mlg.gbadmin.config.TokenService
import com.mlg.gbadmin.dto.user.AuthDto
import com.mlg.gbadmin.dto.user.AuthResponseDto
import com.mlg.gbadmin.dto.user.UserRegisterDto
import com.mlg.gbadmin.model.User
import com.mlg.gbadmin.service.UserService
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/auth")
@Slf4j
class UserController {

    @Autowired
    private UserService service

    @Autowired
    private TokenService tokenService

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    ResponseEntity login(@RequestBody @Valid AuthDto data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email, data.password);
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }

    @PostMapping("/register")
    ResponseEntity<User> createUser(@RequestBody UserRegisterDto user) {
        log.info("Saving new user")
        return ResponseEntity.ok(service.saveUser(user))
    }
}
