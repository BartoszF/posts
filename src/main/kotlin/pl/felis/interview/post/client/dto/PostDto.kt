package pl.felis.interview.post.client.dto

import pl.felis.interview.post.entity.Post

data class PostDto(
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String,
) {
    companion object {
        fun mapToEntity(post: PostDto, comments: List<CommentDto>): Post {
            return Post(
                    post.id,
                    post.userId,
                    post.title,
                    post.body,
                    comments.map { CommentDto.mapToEntity(it) }
            )
        }
    }
}