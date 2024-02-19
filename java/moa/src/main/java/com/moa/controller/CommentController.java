package com.moa.controller;

import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.service.BoardService;
import com.moa.service.CommentService;
import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentController {
    private CommentService commentService;
    private BoardService boardService;

    @PostMapping(".../{boardId}")
    public String createComment(CommentForm commentForm, @PathVariable("boardId") Long boardId) {
        Board board = boardService.findOne(boardId);
        Comment comment = new Comment(commentForm.getText(), LocalDateTime.now(),board);

        return "";

    }
}
