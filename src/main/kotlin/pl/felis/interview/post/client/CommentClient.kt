package pl.felis.interview.post.client

import pl.felis.interview.post.client.dto.CommentDto

interface CommentClient {
    fun findAll(): List<CommentDto>
    fun findById(commentId: Long): CommentDto?
    fun findAllByPostId(postId: Long): List<CommentDto>
}