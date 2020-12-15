package pl.felis.interview.post.entity

import pl.felis.interview.post.dto.PostDto

data class Post(
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String
) {
    companion object {
        fun mapToDto(post: Post, comments: List<Comment>): PostDto {
            return PostDto(
                    post.id,
                    post.userId,
                    post.title,
                    post.body,
                    comments.map { Comment.mapToDto(it) }
            )
        }
    }
}