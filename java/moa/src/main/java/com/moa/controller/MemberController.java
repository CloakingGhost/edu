package com.moa.controller;

import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMember";
    }
    @PostMapping("/members/new")
    public String create(MemberForm memberForm){
        Member member = new Member(memberForm.getName(), RoleStatus.USER);
        memberService.join(member);
        return "redirect:/";
    }
}
