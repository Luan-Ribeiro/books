package com.estudo.books.model

import com.estudo.books.enums.BookStatus
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.sql.Date

@Entity(name = "Book")
data class BookModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus,

    @Column
    @CreationTimestamp
    var createdAt: Date? = null,

    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: AuthorModel? = null,
)