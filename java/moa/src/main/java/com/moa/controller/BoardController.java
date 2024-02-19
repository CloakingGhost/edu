package com.moa.controller;

import com.moa.domain.Board;
import com.moa.domain.Comment;
import com.moa.domain.Member;
import com.moa.service.BoardService;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    // 페이징 : 아직안함
    @GetMapping("/boards/list")
    public String boardList(Model model) {
        List<Board> boards = boardService.findAll();
        model.addAttribute("boards", boards);
        return "boards/list";
    }

    @GetMapping("/boards/new")
    public String createBoardForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "boards/boardForm";
    }

    @PostMapping("/boards/new")
    public String createBoard(BoardForm boardForm, HttpServletRequest request) {
        MemberForm mForm = (MemberForm)request.getSession().getAttribute("user");
        Optional<Member> member = memberService.findOne(mForm.getId());
        Board board;
        if (member.isPresent()) {
            board = new Board(boardForm.getTitle(), boardForm.getContent(), LocalDateTime.now(), member.get());
            boardService.save(board);
        }


        return "redirect:/boards/list";
    }


}
