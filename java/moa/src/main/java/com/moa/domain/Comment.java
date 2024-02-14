package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String text;

    private LocalDateTime commentDate;

    //다대일
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    @Setter
    private Board board;

    //일대다
    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL)
    private final List<Reply> replies = new ArrayList<>();

    // 기본생성자 사용 금지
    protected Comment() {
    }

    public Comment(String text, LocalDateTime commentDate, Board board) {
        this.text = text;
        this.commentDate = commentDate;
        this.board = board;
    }
}
