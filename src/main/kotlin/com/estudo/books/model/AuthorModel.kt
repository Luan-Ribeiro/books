package com.estudo.books.model

import com.estudo.books.enums.AuthorStatus
import com.estudo.books.enums.Role
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Date

@Entity(name = "Author")
data class AuthorModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: AuthorStatus,

    @Column
    @CreationTimestamp
    var createdAt: Date? = null,

    @Column
    val password: String? = null,

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
    @CollectionTable(name = "author_roles", joinColumns = [JoinColumn(name = "author_id")])
    var roles: Set<Role> = setOf()
)