package com.estudo.books.security

import com.estudo.books.enums.AuthorStatus
import com.estudo.books.model.AuthorModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AuthorCustomDetails(
    val authorModel: AuthorModel
) : UserDetails {
    val id = authorModel.id
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        authorModel.roles.map { SimpleGrantedAuthority(it.description) }.toMutableList()
    override fun getPassword(): String = authorModel.password.toString()
    override fun getUsername(): String = authorModel.id.toString()
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = authorModel.status == AuthorStatus.ACTIVE

}