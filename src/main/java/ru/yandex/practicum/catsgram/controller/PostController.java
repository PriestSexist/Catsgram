package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> findAll(@RequestParam (value = "page", defaultValue = "0", required = false) int from,
                              @RequestParam (value = "size", defaultValue = "10", required = false) int size,
                              @RequestParam (value = "sort", defaultValue = "desc", required = false) String sort) {
        return postService.findAll(from, size, sort);
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id){
        return postService.getPostById(id);
    }

    @PostMapping()
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }
}