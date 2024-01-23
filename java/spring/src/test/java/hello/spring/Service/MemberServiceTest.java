package hello.spring.Service;

import hello.spring.domain.Member;
import hello.spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach() {
        this.memoryMemberRepository = new MemoryMemberRepository();
        this.memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long result = memberService.join(member);

        // then
        Member findMember = memberService.findOne(result).get();
        Assertions.assertThat(findMember).isEqualTo(member);
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
        Assertions.assertThat(result).contains(member1, member2, member3);
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
        Assertions.assertThat(result).isEqualTo(member);
    }
}