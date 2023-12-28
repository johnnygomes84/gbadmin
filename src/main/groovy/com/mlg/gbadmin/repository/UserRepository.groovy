package com.mlg.gbadmin.repository

import com.mlg.gbadmin.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.security.core.userdetails.UserDetails

interface UserRepository extends MongoRepository<User, String> {

    UserDetails findByEmail(String email)

}