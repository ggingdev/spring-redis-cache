package io.cloudtype.springredis.controller;

import io.cloudtype.springredis.model.Posts;
import io.cloudtype.springredis.service.PostsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping
    public List<Posts> getAllPosts() {
        return postsService.getAllPosts();
    }

    @PostMapping
    public Posts savePost(@RequestBody Posts post) {
        return postsService.savePost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable Long id) {
        postsService.deletePost(id);
    }
}
