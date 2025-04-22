package org.example.boardoptimize

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long> {

    fun findByBoardId(boardId: Long, pageable: Pageable): Page<Post>
}