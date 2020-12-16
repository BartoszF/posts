package pl.felis.interview.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.client.dto.CommentDto
import pl.felis.interview.post.client.dto.PostDto
import pl.felis.interview.post.client.impl.RestCommentClient
import pl.felis.interview.post.client.impl.RestPostClient
import pl.felis.interview.post.service.PostService
import pl.felis.interview.post.service.PostServiceImpl

@ExtendWith(MockitoExtension::class)
class PostServiceTest {


    @Test
    fun getAll_postWithoutComments_postReturned() {
        //given
        val post = PostDto(1, 1, "Title", "body")
        val comments: List<CommentDto> = emptyList()
        val postService = mockPostService(listOf(post), comments)

        //when
        val returnedPosts = postService.getAllPosts()

        //then
        assertThat(returnedPosts).containsOnly(PostDto.mapToEntity(post, comments))
    }

    @Test
    fun getAll_postWithComment_postReturned() {
        //given
        val post = PostDto(1, 1, "Title", "body")
        val comment = CommentDto(1, 1, "name", "email", "body")
        val postService = mockPostService(listOf(post), listOf(comment))

        //when
        val returnedPosts = postService.getAllPosts()

        //then
        assertThat(returnedPosts).containsOnly(PostDto.mapToEntity(post, listOf(comment)))
    }

    @Test
    fun getAll_postWithCommentAndPostWithoutComment_allReturned() {
        //given
        val posts = listOf(
                PostDto(1, 1, "Title", "body"),
                PostDto(2, 2, "Title2", "body2")
        )
        val comments = mapOf(1L to listOf(CommentDto(1, 1, "name", "email", "body")))
        val postService = mockPostService(posts, comments.values.flatten().toList())

        //when
        val returnedPosts = postService.getAllPosts()
        val expectedPosts = posts.map { PostDto.mapToEntity(it, comments[it.id] ?: emptyList()) }.toList()

        //then
        assertThat(returnedPosts).containsAll(expectedPosts)
    }

    private fun mockPostService(posts: List<PostDto>, comments: List<CommentDto>): PostService {
        return PostServiceImpl(mockPostRepository(posts), mockCommentRepository(comments))
    }

    private fun mockPostRepository(data: List<PostDto>): PostClient {
        val postRepository: PostClient = mock(RestPostClient::class.java)
        `when`(postRepository.findAll()).thenReturn(data)
        return postRepository
    }

    private fun mockCommentRepository(data: List<CommentDto>): CommentClient {
        val commentRepository: CommentClient = mock(RestCommentClient::class.java)
        data.groupBy { it.postId }.forEach { (postId, comments) ->
            `when`(commentRepository.findAllByPostId(postId)).thenReturn(comments)
        }
        return commentRepository
    }
}