package pl.felis.interview.post.dto

data class CommentDto(
        val id: Long,
        val postId: Long,
        val name: String,
        val email: String,
        val body: String
)