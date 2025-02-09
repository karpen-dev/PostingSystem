package com.karpen.postsys.controller;

import com.karpen.postsys.model.Post;
import com.karpen.postsys.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    PostService postService = new PostService();

    @PostMapping
    public String addPost(@RequestBody Post post){
        boolean status = postService.addNewPost(post.getTitle(), post.getDescription(), post.getAuthor());

        if (status){

            return "Done";
        } else {
            return "Error creating post";
        }
    }

    @GetMapping
    public List<Post> getAllPosts(){
        return postService.showAllPosts();
    }

    @DeleteMapping
    public String deletePost(@RequestParam("title") String title){
        if (title.isEmpty()){
            return "Not found this post";
        } else {
            boolean found = postService.foundPost(title);

            if (found){
                boolean status = postService.deletePost(title);

                if (status){
                    return "Done";
                } else {
                    return "Error deleting post";
                }
            } else {
                return "Error, this post is not found";
            }
        }
    }
}
