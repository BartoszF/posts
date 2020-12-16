package pl.felis.interview.post.service

import org.springframework.stereotype.Service
import pl.felis.interview.common.exception.EntityFetchFailureException
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.client.dto.PostDto
import pl.felis.interview.post.entity.Post

@Service
class PostServiceImpl(
        private val postClient: PostClient,
        private val commentsClient: CommentClient
) : PostService {
    @Throws(EntityFetchFailureException::class)
    override fun getAllPosts(): List<Post> {
        try {
            return postClient.findAll().map { PostDto.mapToEntity(it, commentsClient.findAllByPostId(it.id)) }
        } catch (ex: Throwable) {
            throw EntityFetchFailureException(ex)
        }
    }
}