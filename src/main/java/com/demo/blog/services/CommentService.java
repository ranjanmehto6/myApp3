package com.demo.blog.services;


import com.demo.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    public void deleteComment(long postId);
    CommentDto getCommentById(long postId);
    public List<CommentDto> getAllPost();
    List<CommentDto> getCommentByPostId(long postId);

    CommentDto getCommentById(Long postId, Long commentId);

    Void deleteCommentById(Long postId, Long commentId);
}