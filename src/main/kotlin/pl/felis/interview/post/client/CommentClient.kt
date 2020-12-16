package pl.felis.interview.post.client

import pl.felis.interview.post.entity.Comment

interface CommentClient {
    fun findAll(): List<Comment>
    fun findById(commentId: Long): Comment?
    fun findAllByPostId(postId: Long): List<Comment>
}