package pl.felis.interview.post.client

import pl.felis.interview.post.dto.PostDto

interface PostClient {
    fun findAll(): List<PostDto>
    fun findById(postId: Long): PostDto?
}