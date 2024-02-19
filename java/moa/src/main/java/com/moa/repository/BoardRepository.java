package com.moa.repository;

import com.moa.domain.Board;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findById(Long id) {
        return em.find(Board.class, id);
    }

    //fetch join 사용
    //Dto로 받아야함
    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public List<Board> findAllWithJoin() {
        String select = "b.board_id, b.member_id, b.title, b.content, b.post_date, c.comment_id, c.text, c.comment_date, r.reply_id, r.reply_text, r.reply_date";
        String jpql = "select " + select +
                " from Board b join Comment c on b.id = c.board.id join Reply r on c.id = r.comment.id";
        return em.createQuery(jpql, Board.class)
                .getResultList();
    }

    public List<Board> findAllWithFetch() {
        String jpql = "select b from Board b" +
                " left join fetch b.comments p" +
                " left join fetch p.childs c";
        return em.createQuery(jpql, Board.class)
                .getResultList();
    }
}
