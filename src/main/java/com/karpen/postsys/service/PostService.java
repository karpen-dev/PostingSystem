package com.karpen.postsys.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karpen.postsys.model.Post;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private Post post = new Post();
    private List<Post> posts = new ArrayList<>();

    private ObjectMapper objectMapper = new ObjectMapper();

    public boolean addNewPost(String title, String description, String author){
        loadPosts();

        post.setTitle(title);
        post.setDescription(description);
        post.setAuthor(author);
        posts.add(post);

        savePosts();

        return true;
    }

    public List<Post> showAllPosts(){
        loadPosts();

        return posts;
    }

    public boolean deletePost(String title) {
        loadPosts();

        boolean removed = posts.removeIf(post -> post.getTitle().equals(title));

        savePosts();

        return removed;
    }

    public boolean foundPost(String title) {
        loadPosts();

        return posts.stream().anyMatch(post -> post.getTitle().equals(title));
    }


    private void savePosts(){
        try {
            objectMapper.writeValue(new File("posts.json"), posts);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void loadPosts(){
        try {
            posts = objectMapper.readValue(new File("posts.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Post.class));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
