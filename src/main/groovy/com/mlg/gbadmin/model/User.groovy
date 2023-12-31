package com.mlg.gbadmin.model

import com.mlg.gbadmin.enums.RoleEnum
import groovy.transform.Canonical
import groovyjarjarantlr4.v4.runtime.misc.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Canonical
@Document
class User implements Serializable, UserDetails {
    private static final long serialVersionUID = 1L

    @Id
    @NotNull
    String id
    @NotNull
    @Indexed
    String email
    @NotNull
    String password
    @NotNull
    RoleEnum role
    @NotNull
    String name
    @NotNull
    String lastName

    @Override
    Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == RoleEnum.ROLE_ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_SUPPORT"));
        else return List.of(new SimpleGrantedAuthority("ROLE_SUPPORT"));
    }

    @Override
    String getUsername() {
        return email
    }

    @Override
    boolean isAccountNonExpired() {
        return true
    }

    @Override
    boolean isAccountNonLocked() {
        return true
    }

    @Override
    boolean isCredentialsNonExpired() {
        return true
    }

    @Override
    boolean isEnabled() {
        return true
    }
}
