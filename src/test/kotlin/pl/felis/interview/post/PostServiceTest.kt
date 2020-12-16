package pl.felis.interview.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import pl.felis.interview.post.client.CommentClient
import pl.felis.interview.post.client.PostClient
import pl.felis.interview.post.client.impl.RestCommentClient
import pl.felis.interview.post.client.impl.RestPostClient
import pl.felis.interview.post.entity.Comment
import pl.felis.interview.post.entity.Post
import pl.felis.interview.post.service.PostService
import pl.felis.interview.post.service.PostServiceImpl

@ExtendWith(MockitoExtension::class)
class PostServiceTest {


    @Test
    fun getAll_postWithoutComments_postReturned() {
        //given
        val post = Post(1, 1, "Title", "body")
        val comments: List<Comment> = emptyList()
        val postService = mockPostService(listOf(post), comments)

        //when
        val returnedPosts = postService.getAllPosts()

        //then
        assertThat(returnedPosts).containsOnly(Post.mapToDto(post, comments))
    }

    @Test
    fun getAll_postWithComment_postReturned() {
        //given
        val post = Post(1, 1, "Title", "body")
        val comment = Comment(1, 1, "name", "email", "body")
        val postService = mockPostService(listOf(post), listOf(comment))

        //when
        val returnedPosts = postService.getAllPosts()

        //then
        assertThat(returnedPosts).containsOnly(Post.mapToDto(post, listOf(comment)))
    }

    @Test
    fun getAll_postWithCommentAndPostWithoutComment_allReturned() {
        //given
        val posts = listOf(
                Post(1, 1, "Title", "body"),
                Post(2, 2, "Title2", "body2")
        )
        val comments = mapOf(1L to listOf(Comment(1, 1, "name", "email", "body")))
        val postService = mockPostService(posts, comments.values.flatten().toList())

        //when
        val returnedPosts = postService.getAllPosts()
        val expectedPosts = posts.map { Post.mapToDto(it, comments[it.id] ?: emptyList()) }.toList()

        //then
        assertThat(returnedPosts).containsAll(expectedPosts)
    }

    private fun mockPostService(posts: List<Post>, comments: List<Comment>): PostService {
        return PostServiceImpl(mockPostRepository(posts), mockCommentRepository(comments))
    }

    private fun mockPostRepository(data: List<Post>): PostClient {
        val postRepository: PostClient = mock(RestPostClient::class.java)
        `when`(postRepository.findAll()).thenReturn(data)
        return postRepository
    }

    private fun mockCommentRepository(data: List<Comment>): CommentClient {
        val commentRepository: CommentClient = mock(RestCommentClient::class.java)
        data.groupBy { it.postId }.forEach { (postId, comments) ->
            `when`(commentRepository.findAllByPostId(postId)).thenReturn(comments)
        }
        return commentRepository
    }
}