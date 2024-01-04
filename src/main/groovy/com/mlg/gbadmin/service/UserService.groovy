package com.mlg.gbadmin.service

import com.mlg.gbadmin.config.TokenService
import com.mlg.gbadmin.dto.user.AuthResponseDto
import com.mlg.gbadmin.dto.user.UserRegisterDto
import com.mlg.gbadmin.exception.EntityNotFoundException
import com.mlg.gbadmin.model.SearchContext
import com.mlg.gbadmin.model.User
import com.mlg.gbadmin.repository.UserRepository
import groovy.transform.TupleConstructor
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@TupleConstructor(includeFields = true, defaults = false)
class UserService implements UserDetailsService {

    private final UserRepository repository
    private final TokenService tokenService

    @Transactional
    User saveUser(UserRegisterDto user) {
        User newUser = new User(id: null,
                                email: user.email,
                                password: new BCryptPasswordEncoder().encode(user.password),
                                role: user.role,
                                name: user.name,
                                lastName: user.lastName,
                                firstLogin: user.firstLogin)

        User savedUser = repository.save(newUser)
        savedUser.password = null
        savedUser
    }

    @Transactional
    User updateUser(UserRegisterDto user) {

        User currUser = repository.findById(user.id)
                .orElseThrow(() -> new EntityNotFoundException("User with Id $user.id not found"))
        User newUser = new User(id: user.id,
                email: user.email,
                password: user.password,
                role: user.role,
                name: user.name,
                lastName: user.lastName,
                firstLogin: user.firstLogin)

        User savedUser = repository.save(newUser)
        savedUser.password = null
        savedUser
    }

    AuthResponseDto getLoginCredentials(Authentication auth) {
        def token = tokenService.generateToken((User) auth.getPrincipal())
        new AuthResponseDto(token:token)
    }

    User getUserById(String id) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: $id"))
    }

    Page<User> findAll(SearchContext searchContext) {
        repository.findAll(PageRequest.of(searchContext.page, searchContext.size))
    }

    Boolean deleteById(String id) {
        repository.deleteById(id)
        true
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        repository.findByEmail(username)
    }
}
