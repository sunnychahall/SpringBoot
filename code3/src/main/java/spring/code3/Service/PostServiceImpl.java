package spring.code3.Service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.code3.DTO.PostDTO;
import spring.code3.Entity.PostEntity;
import spring.code3.Exceptions.ResourceNotFoundException;
import spring.code3.Repo.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{


    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPostDTO) {
        PostEntity postEntity = modelMapper.map(inputPostDTO, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("post not found with id: " + id));
        return modelMapper.map(postEntity, PostDTO.class);
    }
}
