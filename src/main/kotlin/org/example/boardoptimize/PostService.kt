package org.example.boardoptimize

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {

    fun getPostsByBoardId(boardId: Long, page: Int, size: Int): Page<Post> {

        val pageable = PageRequest.of(
            page,
            size,
            Sort.by(Sort.Direction.DESC, "createAt")
        )

        return postRepository.findByBoardId(
            boardId = boardId,
            pageable = pageable
        )
    }
}