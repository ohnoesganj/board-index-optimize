package org.example.boardoptimize

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Post (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Long,

    @Column
    val title: String,

    @Column
    val content: String,

    @Column
    val boardId: Long,

    @Column
    val writerId: Long,

    @Column
    val createAt: LocalDateTime,

    @Column
    val updateAt: LocalDateTime,
)