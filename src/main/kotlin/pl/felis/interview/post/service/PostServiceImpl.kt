package pl.felis.interview.post.service

import org.springframework.stereotype.Service
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.dto.PostDto
import pl.felis.interview.post.entity.Post

@Service
class PostServiceImpl(
        private val postClient: PostClient,
        private val commentsClient: CommentClient
) : PostService {
    override fun getAllPosts(): List<Post> {
        return postClient.findAll().map { PostDto.mapToEntity(it, commentsClient.findAllByPostId(it.id)) }
    }
}