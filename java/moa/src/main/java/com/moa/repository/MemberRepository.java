package com.moa.repository;

import com.moa.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Member findByName(String name) {
        return em.createQuery("select m from Member m where name = :name", Member.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public void deleteById(Long memberId) {
        em.remove(findById(memberId));
    }
}
