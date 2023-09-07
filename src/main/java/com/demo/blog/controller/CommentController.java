package com.demo.blog.controller;

import com.demo.blog.payload.CommentDto;
import com.demo.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/posts/1/comments
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDto commentDto)

    {
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") long postId){
        commentService.deleteComment(postId);
        return new ResponseEntity<>("POST IS DELETED",HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public CommentDto getCommentBYId(@PathVariable(value = "postId") long postId){
        CommentDto commentById = commentService.getCommentById(postId);
        return new ResponseEntity<>(commentById, HttpStatus.OK).getBody();
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") Long postId){
        return commentService.getCommentByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentsById(@PathVariable(value = "postId") Long postId,
                                      @PathVariable(value = "commentId") Long commentId){
        return commentService.getCommentById(postId,commentId);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentsById(@PathVariable(value = "postId") Long postId,
                                                     @PathVariable(value = "commentId") Long commentId){
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}