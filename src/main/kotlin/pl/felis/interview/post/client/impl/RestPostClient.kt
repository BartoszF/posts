package pl.felis.interview.post.client.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.dto.PostDto

@Service
class RestPostClient(
        @Value("\${client.post.url}") private val postsUrl: String,
        private val restTemplate: RestTemplate
) : PostClient {
    override fun findAll(): List<PostDto> {
        return restTemplate.getForObject(postsUrl, Array<PostDto>::class.java)?.toList() ?: emptyList()
    }

    override fun findById(postId: Long): PostDto? {
        return restTemplate.getForObject("$postsUrl/$postId", PostDto::class.java)
    }
}