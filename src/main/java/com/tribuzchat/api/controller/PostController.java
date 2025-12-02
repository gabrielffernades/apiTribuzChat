package com.tribuzchat.api.controller;

import com.tribuzchat.api.model.Post;
import com.tribuzchat.api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

//------------------------------------------------------------------------------------------

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarPost(@RequestBody Post post){
        postService.criarPost(post);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Post> buscarTodosOsPost(){ return postService.buscarTodosOsPost(); }

}
