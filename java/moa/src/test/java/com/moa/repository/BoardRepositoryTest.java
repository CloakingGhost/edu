package com.moa.repository;

import com.moa.domain.*;
import com.moa.service.BoardService;
import com.moa.service.CommentService;
import com.moa.service.MemberService;
import com.moa.service.ReplyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BoardService boardService;
    @Autowired
    MemberService memberService;
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;

    @Test
    void saveRepo() {
        //given
        Member m = new Member("test", RoleStatus.USER);
        memberRepository.save(m);
        Member findM = memberRepository.findByName("test");
        Board b = new Board("title", "content", LocalDateTime.now(), findM);


        //when
        boardRepository.save(b);
        Board findBoard = boardRepository.findById(1L);

        //then
        System.out.println(findBoard.getContent());
        Assertions.assertThat(findBoard.getTitle()).isEqualTo("title");
    }

    @Test
    void saveServ() {
        //given
        Member m = new Member("test", RoleStatus.USER);
        memberService.join(m);
        Member findM = memberService.findOne("test");
        Board b = new Board("title", "content", LocalDateTime.now(), findM);

        //when
        boardService.save(b);
        Board findBoard = boardService.findOne(1L);

        //then
        Assertions.assertThat(findBoard.getTitle()).isEqualTo("title");

    }

    @Test
    void comment() {
        //given
        //회원
        Member m1 = new Member("test1", RoleStatus.USER);
        Member m2 = new Member("test2", RoleStatus.USER);
        memberService.join(m1);
        memberService.join(m2);

        //게시글
        Member findM1 = memberService.findOne("test1");
        Member findM2 = memberService.findOne("test2");

        Board b1 = new Board("title1", "content1", LocalDateTime.now(), findM1);
        Board b2 = new Board("title2", "content2", LocalDateTime.now(), findM2);
        boardService.save(b1);
        boardService.save(b2);

        Board findBoard1 = boardService.findOne(1L);
        Board findBoard2 = boardService.findOne(2L);

        //댓글
        Comment c1 = new Comment("부모댓글1", LocalDateTime.now(), findBoard1);
        Comment c2 = new Comment("부모댓글2", LocalDateTime.now(), findBoard2);

        //when
        //등록
        commentService.save(c1);
        commentService.save(c2);
        List<Comment> result1 = commentService.findAll();


        //then
        Assertions.assertThat(result1.size()).isEqualTo(2);

        for (Comment comment : result1) {
            System.out.println("comment.getBoard() = " + comment.getBoard());
            System.out.println("comment.getBoard() = " + comment.getText());
        }

    }

    @Test
    void reply() {
        //given
        //회원
        Member m1 = new Member("test1", RoleStatus.USER);
        Member m2 = new Member("test2", RoleStatus.USER);
        memberService.join(m1);
        memberService.join(m2);

        Member findM1 = memberService.findOne("test1");
        Member findM2 = memberService.findOne("test2");

        //게시글
        Board b1 = new Board("title1", "content1", LocalDateTime.now(), findM1);
        Board b2 = new Board("title2", "content2", LocalDateTime.now(), findM2);
        boardService.save(b1);
        boardService.save(b2);

        Board findBoard1 = boardService.findOne(1L);
        Board findBoard2 = boardService.findOne(2L);

        //댓글
        Comment c1 = new Comment("부모댓글1", LocalDateTime.now(), findBoard1);
        Comment c2 = new Comment("부모댓글2", LocalDateTime.now(), findBoard2);
        commentService.save(c1);
        commentService.save(c2);

        Comment findC1 = commentService.findOne(1L);
        Comment findC2 = commentService.findOne(2L);

        //대댓글
        Reply r1_1 = new Reply("부모1댓글의 대댓글1", LocalDateTime.now(), findC1);
        Reply r1_2 = new Reply("부모1댓글의 대댓글2", LocalDateTime.now(), findC1);
        Reply r2_1 = new Reply("부모2댓글의 대댓글1", LocalDateTime.now(), findC2);
        Reply r2_2 = new Reply("부모2댓글의 대댓글2", LocalDateTime.now(), findC2);

        //when
        //등록
        replyService.save(r1_1);
        replyService.save(r1_2);
        replyService.save(r2_1);
        replyService.save(r2_2);
        List<Comment> result1 = commentService.findAll();
        List<Reply> result2 = replyService.findAllById(1L);
        List<Reply> result3 = replyService.findAllById(2L);


        //then
        Assertions.assertThat(result1.size()).isEqualTo(2);
        Assertions.assertThat(result2.size()).isEqualTo(2);
        Assertions.assertThat(result3.size()).isEqualTo(2);

        for (Comment comment : result1) {
            System.out.println("comment.getBoard().getComments().size() = " + comment.getBoard().getComments().size());
            System.out.println("comment.getText() = " + comment.getText());
        }
        for (Reply reply : result2) {
            System.out.println("reply.getId() = " + reply.getId());
            System.out.println("reply.getText() = " + reply.getText());
            System.out.println("reply.getComment().getText() = " + reply.getComment().getText());
        }
        for (Reply reply : result3) {
            System.out.println("reply.getId() = " + reply.getId());
            System.out.println("reply.getText() = " + reply.getText());
            System.out.println("reply.getComment().getText() = " + reply.getComment().getText());
        }

    }

    @Test
    @Rollback(value = false)
    void join() {
        //given
        //회원
        Member m1 = new Member("test1", RoleStatus.USER);
        Member m2 = new Member("test2", RoleStatus.USER);
        memberService.join(m1);
        memberService.join(m2);

        Member findM1 = memberService.findOne("test1");
        Member findM2 = memberService.findOne("test2");

        //게시글
        Board b1 = new Board("title1", "content1", LocalDateTime.now(), findM1);
        Board b2 = new Board("title2", "content2", LocalDateTime.now(), findM2);
        boardService.save(b1);
        boardService.save(b2);

        Board findBoard1 = boardService.findOne(1L);
        Board findBoard2 = boardService.findOne(2L);

        //댓글
        Comment c1 = new Comment("부모댓글1", LocalDateTime.now(), findBoard1);
        Comment c2 = new Comment("부모댓글2", LocalDateTime.now(), findBoard2);
        commentService.save(c1);
        commentService.save(c2);

        Comment findC1 = commentService.findOne(1L);
        Comment findC2 = commentService.findOne(2L);

        //대댓글
        Reply r1_1 = new Reply("부모1댓글의 대댓글1", LocalDateTime.now(), findC1);
        Reply r1_2 = new Reply("부모1댓글의 대댓글2", LocalDateTime.now(), findC1);
        Reply r2_1 = new Reply("부모2댓글의 대댓글1", LocalDateTime.now(), findC2);
        Reply r2_2 = new Reply("부모2댓글의 대댓글2", LocalDateTime.now(), findC2);

        //when
        //등록
        replyService.save(r1_1);
        replyService.save(r1_2);
        replyService.save(r2_1);
        replyService.save(r2_2);

        List<Board> findBoards = boardRepository.findAllWithFetch();

//        for (Board findBoard : findBoards) {
//            System.out.println("findBoard.getComments() = " + findBoard.getComments());
//            System.out.println("findBoard.getComments() = " + findBoard.getComments().get(0).getReplies());
//            System.out.println("findBoard.getComments() = " + findBoard.getComments().get(1).getReplies());
//        }
        //then




    }
}