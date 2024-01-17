package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // lombok 추가 후, 2개의 빈을 찾을 때 어떻게 하는지 확인하기 위해 추가함
//@Qualifier("fixDiscountPolicy") // 이름 지정 @Primary가 있는 빈이 우선시 됨
public class FixDiscountPolicy implements DiscountPolicy{
    private int discountFixAmount = 1000; // 1000원 할인
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
