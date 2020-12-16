package pl.felis.interview.post.dto

data class PostDto(
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String,
        val comments: List<CommentDto>
)