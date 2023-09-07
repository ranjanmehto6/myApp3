package com.demo.blog.controller;

import com.demo.blog.payload.PostDto;
import com.demo.blog.payload.PostResponse;
import com.demo.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Autowired
    private PostService postService;

//    public PostController(PostService postService) {
//        this.postService = postService;
//    }

    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result) {
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted",HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto)
    {
        PostDto postDto1 = postService.updatePost(id, postDto);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <PostDto> getPostById(@PathVariable("id") long id){
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);

    }
    //http://localhost:8080/api/post?pageNo=0&pageSize=10&sortBY=id&sortDir=asc
    @GetMapping
    public PostResponse getPosts(
            @RequestParam(value = "pageNo", defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        //  PostResponse postResponse =  postService.getPosts(pageNo,pageSize,sortBy,sortDir);
        return postService.getPosts(pageNo,pageSize,sortBy,sortDir);

    }

}