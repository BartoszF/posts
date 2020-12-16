package pl.felis.interview.post.entity

data class Post(
        val id: Long,
        val userId: Long,
        val title: String,
        val body: String,
        val comments: List<Comment>
)