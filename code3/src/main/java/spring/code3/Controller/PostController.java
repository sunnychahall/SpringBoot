package spring.code3.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.code3.DTO.PostDTO;
import spring.code3.Service.PostService;

import java.util.List;


@RestController
@RequestMapping(path = "/Posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPost()
    {
        return postService.getAllPosts();
    }


    @GetMapping(path = "/{postId}")
    public PostDTO getById(@PathVariable Long postId)
    {
        return postService.getPostById(postId);
    }


    @PostMapping
    public PostDTO createPost(@RequestBody PostDTO inputPostDTO)
    {
        return postService.createNewPost(inputPostDTO);
    }
}
