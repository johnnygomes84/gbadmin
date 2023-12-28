package com.mlg.gbadmin.dto.user

import com.mlg.gbadmin.enums.RoleEnum

record UserRegisterDto(String email, String password, RoleEnum role) {

}