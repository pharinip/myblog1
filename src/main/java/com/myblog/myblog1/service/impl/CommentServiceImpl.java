package com.myblog.myblog1.service.impl;

import com.myblog.myblog1.entity.Comment;
import com.myblog.myblog1.entity.Post;
import com.myblog.myblog1.exception.ResourceNotFoundException;
import com.myblog.myblog1.exception.UnsupportedException;
import com.myblog.myblog1.payload.CommentDto;
import com.myblog.myblog1.repository.CommentRepository;
import com.myblog.myblog1.repository.PostRepository;
import com.myblog.myblog1.service.CommentService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;
    private CommentRepository commentRepository;

    private ModelMapper modelMapper;


    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper=modelMapper;

    }

    @Override
    public CommentDto createComment(CommentDto commentDto,long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with Id:" + postId)
        );
        Comment comment=new Comment();
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());

        comment.setText(commentDto.getText());
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto=new CommentDto();
        dto.setId(savedComment.getId());
        dto.setEmail(savedComment.getEmail());
        dto.setText(savedComment.getText());

        return dto;
    }


    @Override
    public void deleteComment(long id) {


        commentRepository.deleteById(id);

    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found for the Id:" + id)
        );

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("comment not found for Id:" + id)
        );
        Comment c = mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        long id1 = post.getId();
        System.out.println("id1id1id1id1id1:"+id1);

        Comment savedComment = commentRepository.save(c);

        return  maptoDto(savedComment);


    }

    CommentDto maptoDto(Comment comment){
        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        return dto;
    }

    Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }


}
