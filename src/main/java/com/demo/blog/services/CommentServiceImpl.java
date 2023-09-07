package com.demo.blog.services;

import com.demo.blog.entities.Comment;
import com.demo.blog.entities.Post;
import com.demo.blog.exception.ResourceNotFound;
import com.demo.blog.payload.CommentDto;
import com.demo.blog.repositry.CommentRepositry;
import com.demo.blog.repositry.PostRepositry;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepositry commentRepositry;
    private PostRepositry postRepositry;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepositry commentRepositry, PostRepositry postRepositry, ModelMapper modelMapper) {
        this.commentRepositry = commentRepositry;
        this.postRepositry = postRepositry;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post =postRepositry.findById(postId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+postId));
        comment.setPost(post);
        Comment save = commentRepositry.save(comment);
        CommentDto dto = mapToDto(save);
        return dto;
    }

    @Override
    public void deleteComment(long postId) {
        commentRepositry.deleteById(postId);

    }

    @Override
    public CommentDto getCommentById(long postId) {
        Comment comment = commentRepositry.findById(postId).orElseThrow(() -> new ResourceNotFound("NOT AVAILAIBLE WITH THIS ID " + postId));
        CommentDto dto1 = mapToDto(comment);
        return dto1;
    }

    @Override
    public List<CommentDto> getAllPost() {
        List<Comment> all = commentRepositry.findAll();
        CommentDto allComment = mapToDto((Comment) all);
        return (List<CommentDto>) allComment;
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        Post post =postRepositry.findById(postId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+postId));
        List<Comment> comment = commentRepositry.findByPostId(postId);
        List<CommentDto> collect = comment.stream().map(comment1 -> mapToDto(comment1)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        Post post =postRepositry.findById(postId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+postId));
        Comment comment =commentRepositry.findById(commentId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+commentId));
        CommentDto dto = mapToDto(comment);

        return dto;
    }

    @Override
    public Void deleteCommentById(Long postId, Long commentId) {
        Post post =postRepositry.findById(postId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+postId));
        Comment comment =commentRepositry.findById(commentId).orElseThrow(()-> new ResourceNotFound("Post not found with id "+commentId));
        commentRepositry.deleteById(commentId);
        return null;
    }


    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto dto = modelMapper.map(comment,CommentDto.class);
        return dto;
    }
}