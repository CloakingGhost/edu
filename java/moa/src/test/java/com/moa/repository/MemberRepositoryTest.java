package com.moa.repository;

import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Transactional
    void save() {
        //given
        Member m = new Member("test1" , RoleStatus.USER);

        //when
        memberRepository.save(m);
        Member result = memberRepository.findByName("test1");

        //then
        Assertions.assertThat(result.getName()).isEqualTo("test1");
        Assertions.assertThat(result.getRole()).isEqualTo(RoleStatus.USER);
        Assertions.assertThat(result.getId()).isEqualTo(1L);
    }

}