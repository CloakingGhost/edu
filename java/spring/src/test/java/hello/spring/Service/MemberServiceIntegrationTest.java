package hello.spring.Service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional // rollback after test
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long result = memberService.join(member);

        // then
        Member findMember = memberService.findOne(result).get();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void ExceptionOfJoin() {
        // given
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring");

        // when, then
        Assertions.assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Already Existence");

    }

    @Test
    void findMembers() {
        // given
        Member member1 = new Member();
        member1.setName("spring1");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberService.join(member2);

        Member member3 = new Member();
        member3.setName("spring3");
        memberService.join(member3);

        // when
        List<Member> result = memberService.findMembers();

        // then
        for (Member m : result) {
            Assertions.assertThat(m.getName()).containsAnyOf(member1.getName(), member2.getName(), member3.getName());

        }
    }

    @Test
    void findOne() {
        // given
        Member member = new Member();
        member.setName("spring");
        Long memberId = memberService.join(member);

        // when
        Member result = memberService.findOne(memberId).get();

        // then
        Assertions.assertThat(result.getName()).isEqualTo(member.getName());
    }
}

