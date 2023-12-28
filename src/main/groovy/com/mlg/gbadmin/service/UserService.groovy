package com.mlg.gbadmin.service

import com.mlg.gbadmin.config.WebSecurity
import com.mlg.gbadmin.dto.user.UserRegisterDto
import com.mlg.gbadmin.model.User
import com.mlg.gbadmin.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository

    @Transactional(rollbackFor = Exception.class)
    User saveUser(UserRegisterDto user) {
        User newUser = new User(id: null, email: user.email, password: new BCryptPasswordEncoder().encode(user.password), role: user.role )

        User savedUser = repository.save(newUser)
        savedUser.password = null
        return savedUser
    }

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
    }
}
