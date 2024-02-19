package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private String text;

    private LocalDateTime replyDate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "member_id")
//    private Member member;

    // 다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    // 기본생성자 사용 금지
    protected Reply(){}

    public Reply(String text, LocalDateTime replyDate, Comment comment){
        this.text = text;
        this.replyDate = replyDate;
        this.comment = comment;
    }
}
