package com.moa.controller;

import com.moa.domain.Board;
import com.moa.domain.Member;
import com.moa.service.BoardService;
import com.moa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    //post
    public String createBoard(BoardForm boardForm) {
        Member member = memberService.findOne(boardForm.getId());
        Board board = new Board(boardForm.getTitle(), boardForm.getContent(), LocalDateTime.now(), member);

        boardService.save(board);

        return null;
    }
}
