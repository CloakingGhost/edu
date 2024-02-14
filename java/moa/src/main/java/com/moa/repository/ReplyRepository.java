package com.moa.repository;

import com.moa.domain.Reply;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public Reply findById(Long replyId) {
        return em.find(Reply.class, replyId);
    }

    public List<Reply> findAll() {
        return em.createQuery("select r from Reply r", Reply.class)
                .getResultList();
    }

    public List<Reply> findAllById(Long commentId) {
        return em.createQuery("select r from Reply r join r.comment c where c.id = :id", Reply.class)
                .setParameter("id", commentId)
                .getResultList();
    }
}
