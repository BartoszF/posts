package pl.felis.interview.post

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import pl.felis.interview.post.service.PostService
import pl.felis.interview.post.writer.PostWriter

@Component
class PostWriterRunner(private val postService: PostService, private val postWriter: PostWriter) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        postWriter.write(postService.getAllPosts())
    }
}