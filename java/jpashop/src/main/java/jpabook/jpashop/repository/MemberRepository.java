package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    //Spring Data JPA에 없는 메소드는 만들어 주어야한다
    //메소드 작성 규칙에 따라 구현을 하지 않아도 알아서 동작하도록 만들어준다
    // select m from Member m where m.name = :name
    List<Member> findByName(String name);
}
