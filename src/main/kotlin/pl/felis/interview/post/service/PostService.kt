package pl.felis.interview.post.service

import pl.felis.interview.post.entity.Post

interface PostService {
    fun getAllPosts(): List<Post>
}