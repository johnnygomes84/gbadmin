package com.mlg.gbadmin.controller

import com.mlg.gbadmin.dto.user.AuthDto
import com.mlg.gbadmin.dto.user.UserRegisterDto
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.User
import com.mlg.gbadmin.service.UserService
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@TupleConstructor(includeFields = true, defaults = false)
@RequestMapping("/api/users")
@Slf4j
@CrossOrigin("*")
class UserController {

    private final UserService service

    @PostMapping("/register")
    ResponseEntity<User> createUser(@RequestBody UserRegisterDto user) {
        log.info("Saving new user")
        return ResponseEntity.ok(service.saveUser(user))
    }

    @PutMapping("/update/{id}")
    ResponseEntity<User> updateUser(@RequestBody UserRegisterDto user) {
        log.info("Updating user")
        return ResponseEntity.ok(service.updateUser(user))
    }

    @GetMapping("/users")
    ResponseEntity<Page<User>> getAllUsers(SearchContext searchContext) {
        log.info("Getting all users")
        return ResponseEntity.ok(service.findAll(searchContext))
    }
}
