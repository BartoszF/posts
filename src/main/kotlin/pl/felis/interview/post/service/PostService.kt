package pl.felis.interview.post.service

import pl.felis.interview.common.exception.EntityFetchFailureException
import pl.felis.interview.post.entity.Post

interface PostService {
    @Throws(EntityFetchFailureException::class)
    fun getAllPosts(): List<Post>
}