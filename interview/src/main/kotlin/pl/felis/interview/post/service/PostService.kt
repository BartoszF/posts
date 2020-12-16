package pl.felis.interview.post.service

import pl.felis.interview.post.dto.PostDto

interface PostService {
    fun getAllPosts(): List<PostDto>
}