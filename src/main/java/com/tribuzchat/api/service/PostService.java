package com.tribuzchat.api.service;

import com.tribuzchat.api.model.Post;
import com.tribuzchat.api.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

//------------------------------------------------------------------------------------------

    public void criarPost(Post post){
        postRepository.save(post);
    }


    public List<Post> buscarTodosOsPost(){
        return postRepository.findAll();
    }


}