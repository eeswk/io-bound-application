package class101.foo.io;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    Producer producer;

    @Autowired
    ObjectMapper objectMapper;


    // 1. 글을 작성한다.
    @PostMapping("/post")
    public Post createPost(@RequestBody Post post) throws JsonProcessingException {
        String jsonPost = objectMapper.writeValueAsString(post);
        System.out.println(jsonPost);
        producer.sendTo(jsonPost);
        return post;
//        return postRepository.save(post);
    }

    
    // 4. 글 내용으로 검색 -> 해당 내용이 포함된 모든 글
    @GetMapping("/search")
    public List<Post> getPostByContent(@RequestParam String content) {
        return postRepository.findByContent(content);
    }
}
