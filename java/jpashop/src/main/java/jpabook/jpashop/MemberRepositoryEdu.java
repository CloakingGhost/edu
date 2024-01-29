package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryEdu {
    private final EntityManager em;

    public Long save(MemberEdu member) {
        em.persist(member);
        return member.getId();
    }

    public MemberEdu find(Long id) {
        return em.find(MemberEdu.class, id);
    }


}
