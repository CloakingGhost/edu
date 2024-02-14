package com.moa.repository;

import com.moa.domain.Comment;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findById(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }
    public List<Comment> findAllById(Long boardId) {
        return em.createQuery("select c from Comment c join c.board b where b.id = :id", Comment.class)
                .setParameter("id", boardId)
                .getResultList();
    }

}
