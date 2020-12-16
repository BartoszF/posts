package pl.felis.interview.post.client.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.dto.CommentDto

@Service
class RestCommentClient(
        @Value("\${client.comment.url}") private val commentsUrl: String,
        private val restTemplate: RestTemplate
) : CommentClient {
    override fun findAll(): List<CommentDto> {
        return restTemplate.getForObject(commentsUrl, Array<CommentDto>::class.java)?.toList() ?: emptyList()
    }

    override fun findById(commentId: Long): CommentDto? {
        return restTemplate.getForObject("$commentsUrl/$commentId", CommentDto::class.java)
    }

    override fun findAllByPostId(postId: Long): List<CommentDto> {
        return restTemplate.getForObject("$commentsUrl?postId=$postId", Array<CommentDto>::class.java)?.toList()
                ?: emptyList()
    }
}