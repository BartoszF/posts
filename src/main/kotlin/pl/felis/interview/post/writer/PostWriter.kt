package pl.felis.interview.post.writer

import pl.felis.interview.common.WriteFailureException
import pl.felis.interview.post.entity.Post

interface PostWriter {
    @Throws(WriteFailureException::class)
    fun write(posts: List<Post>)
}