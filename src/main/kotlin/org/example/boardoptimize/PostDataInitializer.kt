package org.example.boardoptimize

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PostDataInitializer (
    private val postRepository: PostRepository
): CommandLineRunner {

    override fun run(vararg args: String?) {
        println("********** 대량 게시글 삽입 시작 **********")

        // 삽입
        val postList = mutableListOf<Post>()
        val batchSize = 50_000

        for (i in 1..500_000) {
            val post = Post (
                postId = 0,
                title = "title $i",
                content = "content $i",
                boardId = 1,
                writerId = 1,
                createAt = LocalDateTime.now(),
                updateAt = LocalDateTime.now()
            )

            postList.add(post)

            if (postList.size >= batchSize) {
                postRepository.saveAll(postList)
                postList.clear()
            }
        }

        postRepository.saveAll(postList)

        println("********** 대량 게시글 삽입 종료 **********")
    }
}