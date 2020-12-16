package pl.felis.interview.post.client

import pl.felis.interview.post.entity.Post

interface PostClient {
    fun findAll(): List<Post>
    fun findById(postId: Long): Post?
}