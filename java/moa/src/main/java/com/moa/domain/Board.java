package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Length(min = 1, max = 30)
    private String title;

    private String content;

    private LocalDateTime postDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    // 기본생성자 사용 금지
    protected Board() {
    }

    public Board(String title, String content, LocalDateTime postDate, Member member) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.member = member;
    }

    // 양방향으로 참조
    public void setComment(Comment comment) {
        this.comments.add(comment);
        comment.setBoard(this);
    }
}