package com.moa.service;

import com.moa.controller.MemberForm;
import com.moa.domain.Member;
import com.moa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public void join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(value -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<Member> findOne(String memberName) {
        return memberRepository.findByName(memberName);
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Transactional
    public void update(MemberForm memberForm) {
        Optional<Member> findMember = memberRepository.findById(memberForm.getId());
        if (findMember.isPresent()) {
            Member member = findMember.get();

            if (member.getName().equals(memberForm.getName())) return;

            member.setName(memberForm.getName());
        }
    }

    @Transactional
    public boolean retire(Long memberId) {
        memberRepository.deleteById(memberId);
        Optional<Member> findMember = memberRepository.findById(memberId);
        return findMember.isEmpty();
    }
}
