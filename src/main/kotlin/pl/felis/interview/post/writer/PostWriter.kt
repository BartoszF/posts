package pl.felis.interview.post.writer

import pl.felis.interview.post.dto.PostDto
import java.io.IOException

interface PostWriter {
    @Throws(IOException::class)
    fun write(posts: List<PostDto>)
}