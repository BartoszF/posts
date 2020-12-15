package pl.felis.interview.post.entity

import pl.felis.interview.post.dto.CommentDto

data class Comment(
        val id: Long,
        val postId: Long,
        val name: String,
        val email: String,
        val body: String
) {
    companion object {
        fun mapToDto(comment: Comment): CommentDto {
            return CommentDto(
                    comment.id,
                    comment.postId,
                    comment.name,
                    comment.email,
                    comment.body
            )
        }
    }
}