package pl.felis.interview.post.client.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.entity.Comment

@Service
class RestCommentClient(
        @Value("\${client.comment.url}") private val commentsUrl: String,
        private val restTemplate: RestTemplate
) : CommentClient {
    override fun findAll(): List<Comment> {
        return restTemplate.getForObject(commentsUrl, Array<Comment>::class.java)?.toList() ?: emptyList()
    }

    override fun findById(commentId: Long): Comment? {
        return restTemplate.getForObject("$commentsUrl/$commentId", Comment::class.java)
    }

    override fun findAllByPostId(postId: Long): List<Comment> {
        return restTemplate.getForObject("$commentsUrl?postId=$postId", Array<Comment>::class.java)?.toList()
                ?: emptyList()
    }
}