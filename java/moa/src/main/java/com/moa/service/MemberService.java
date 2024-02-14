package com.moa.service;

import com.moa.domain.Member;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public void retire(Long memberId) {
        memberRepository.deleteById(memberId);
    }


    public Member findOne(Object o) {
        if (o instanceof String) {
            return memberRepository.findByName((String) o);
        } else if (o instanceof Long) {
            return memberRepository.findById((Long) o);
        } else {
            // 다른 타입이 들어오면 에러 발생
            throw new IllegalStateException("입력값 확인: " + o);
        }
    }

}
