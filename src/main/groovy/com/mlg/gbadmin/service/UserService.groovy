package com.mlg.gbadmin.service

import com.mlg.gbadmin.config.TokenService
import com.mlg.gbadmin.config.WebSecurity
import com.mlg.gbadmin.dto.user.AuthDto
import com.mlg.gbadmin.dto.user.AuthResponseDto
import com.mlg.gbadmin.dto.user.UserRegisterDto
import com.mlg.gbadmin.exception.EntityNotFoundException
import com.mlg.gbadmin.model.User
import com.mlg.gbadmin.repository.UserRepository
import groovy.transform.TupleConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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

    @Transactional(rollbackFor = Exception.class)
    User saveUser(UserRegisterDto user) {
        User newUser = new User(id: null,
                                email: user.email,
                                password: new BCryptPasswordEncoder().encode(user.password),
                                role: user.role,
                                name: user.name,
                                lastName: user.lastName)

        User savedUser = repository.save(newUser)
        savedUser.password = null
        return savedUser
    }

    AuthResponseDto getLoginCredentials(Authentication auth) {
        def token = tokenService.generateToken((User) auth.getPrincipal())
        return new AuthResponseDto(token:token)
    }

    User getUserById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found for id: $id"))
    }

    Page<User> findAll() {

    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
    }
}
