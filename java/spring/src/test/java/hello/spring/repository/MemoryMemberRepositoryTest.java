package hello.spring.repository;

import hello.spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트 후 실행 할 동작
    @AfterEach
    public void afterEach() {
        repository.clearStore(); // 메모리 정리
    }

    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Member result = repository.save(member);

        //then
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findById() {
        // given
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        // when
        Member result = repository.findById(member.getId()).get();

        // then
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    void findByName() {
        // given
        Member member = new Member();
        member.setName("string");
        repository.save(member);

        // when
        Member result = repository.findByName(member.getName()).get();

        // then
        Assertions.assertThat(member).isEqualTo((result));
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        repository.save(member3);

        // when
        List<Member> result = repository.findAll();

        // then
        Assertions.assertThat(result).contains(member1, member2, member3);
    }
}