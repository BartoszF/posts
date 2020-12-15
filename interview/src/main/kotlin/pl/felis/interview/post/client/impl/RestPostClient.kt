package pl.felis.interview.post.client.impl

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.entity.Post

@Service
class RestPostClient(
        @Value("\${client.post.url}") private val postsUrl: String,
        private val restTemplate: RestTemplate
) : PostClient {
    override fun findAll(): List<Post> {
        return restTemplate.getForObject(postsUrl, Array<Post>::class.java)?.toList() ?: emptyList()
    }

    override fun findById(postId: Long): Post? {
        return restTemplate.getForObject("$postsUrl/$postId", Post::class.java)
    }
}