package com.moa.service;

import com.moa.domain.Comment;
import com.moa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment findOne(Long commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    public List<Comment> findAllById(Long boardId) {
        return commentRepository.findAllById(boardId);
    }

}
