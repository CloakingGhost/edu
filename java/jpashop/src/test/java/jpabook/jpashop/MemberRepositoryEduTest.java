package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
@SpringBootTest
class MemberRepositoryEduTest {

    @Autowired
    MemberRepositoryEdu memberRepositoryEdu;

    @Test
    @Transactional
    public void testMember() throws Exception {
        MemberEdu member = new MemberEdu();
        member.setUserName("memberA");

        Long savedID = memberRepositoryEdu.save(member);
        MemberEdu findMember = memberRepositoryEdu.find(savedID);

        Assertions.assertThat(findMember.getId()).isEqualTo(savedID);
        Assertions.assertThat(findMember.getUserName()).isEqualTo(member.getUserName());
        Assertions.assertThat(findMember).isEqualTo(member);


    }

}