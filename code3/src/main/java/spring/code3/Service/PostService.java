package spring.code3.Service;

import spring.code3.DTO.PostDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAllPosts();


    PostDTO createNewPost(PostDTO inputPostDTO);

    PostDTO getPostById(Long id);

}
