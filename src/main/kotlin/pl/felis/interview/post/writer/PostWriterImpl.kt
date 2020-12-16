package pl.felis.interview.post.writer

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import pl.felis.interview.post.dto.PostDto
import java.io.File
import java.io.IOException

@Service
class PostWriterImpl(private val objectMapper: ObjectMapper, @Value("\${writer.path}") private val directory: String) : PostWriter {
    @Throws(IOException::class)
    override fun write(posts: List<PostDto>) {
        val directoryFile = File(directory)
        if (directoryFile.exists() || directoryFile.mkdir()) {
            posts.forEach {
                objectMapper.writeValue(File(directoryFile.path, "${it.id}.json"), it)
            }
        }
    }
}