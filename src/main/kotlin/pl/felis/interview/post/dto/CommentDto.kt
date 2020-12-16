package pl.felis.interview.post.dto

import pl.felis.interview.post.entity.Comment

data class CommentDto(
        val id: Long,
        val postId: Long,
        val name: String,
        val email: String,
        val body: String
) {
    companion object {
        fun mapToEntity(comment: CommentDto): Comment {
            return Comment(
                    comment.id,
                    comment.postId,
                    comment.name,
                    comment.email,
                    comment.body
            )
        }
    }
}