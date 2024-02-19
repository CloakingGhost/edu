package com.moa.controller;

import com.moa.domain.Member;
import com.moa.domain.RoleStatus;
import com.moa.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/memberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member(memberForm.getName(), RoleStatus.USER);
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members/login")
    public String loginFrom(Model model) {
        model.addAttribute("memberForm", new MemberForm());
//        model.addAttribute("loginFail", model.getAttribute("loginFail"));
        return "members/loginForm";
    }

    //인터셉터로 이동 때 마다 로그인 여부 확인(만들어야함) 혹은 스프링 시큐리티 사용
    @PostMapping("/members/login")
    public String login(MemberForm memberForm, HttpServletRequest request, RedirectAttributes re) {
        Optional<Member> findMember = memberService.findOne(memberForm.getName());
        if (findMember.isEmpty()) {
            // Model에 저장됨
            re.addFlashAttribute("loginFail", "이름을 확인 해주세요");
            return "redirect:/members/login";
        }
        memberForm.setId(findMember.get().getId());
        request.getSession().setAttribute("user", memberForm);
        return "redirect:/";
    }

    @GetMapping("/members/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);// 새로운 세션을 생성하지 않음

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/members/info")
    public String info(Model model, HttpServletRequest request) {
        MemberForm user = (MemberForm) request.getSession().getAttribute("user");

        Optional<Member> findMember = memberService.findOne(user.getId());
        findMember.ifPresent(m ->{
            MemberForm form = new MemberForm();
            form.setName(m.getName());
            form.setRole(m.getRole());

            model.addAttribute("memberForm", form);
        });

//        if(findMember.isPresent()){
//            Member m = findMember.get();
//            MemberForm form = new MemberForm();
//            form.setName(m.getName());
//            form.setRole(m.getRole());
//
//            model.addAttribute("memberForm", form);
//        }
        return "members/info";
    }

    @GetMapping("/members/update")
    public String updateForm(Model model, HttpServletRequest request) {

        MemberForm user = (MemberForm) request.getSession().getAttribute("user");
        if (user == null) return "redirect:/";

        model.addAttribute("memberForm", user);
        return "members/memberUpdate";
    }

    @PostMapping("/members/update")
    public String update(MemberForm memberForm) {
        memberService.update(memberForm);

        return "redirect:/members/info";
    }

    @GetMapping("/members/retire")
    public String retireForm(Model model, HttpServletRequest request) {
        MemberForm user = (MemberForm) request.getSession().getAttribute("user");
        model.addAttribute("memberForm", user);
        return "members/retireForm";
    }

    @PostMapping("/members/retire")
    public String retired(HttpServletRequest request, RedirectAttributes re) {
        HttpSession session = request.getSession();
        MemberForm user = (MemberForm) session.getAttribute("user");
        boolean result = false;

        if (user != null)
            result = memberService.retire(user.getId());

        if (result) {
            // 정상처리 됨
            session.invalidate();
            return "redirect:/";
        }
        re.addFlashAttribute("retireFail", "처리 중에 문제가 발생되었습니다. 다시 한번 시도해주세요.");
        return "redirect:/members/retire";
    }
}