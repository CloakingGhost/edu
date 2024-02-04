package jpabook.jpashop.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/v1/members")
    public List<Member> membersV1() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = memberService.findMembers();

        // 엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());

        // 감싸지 않으면 배열타입으로 나간다
        // wrapping { "data" : [m1, m2, ...] }
        // no-wrapping [m1, m2, ...]
//        return new Result(collect.size(), collect);
        return new Result(collect);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
//        private int count;
        private T data;
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String name;
    }

    //엔티티의 필드 이름만 바뀌어도 모든 스펙이 변하는 치명적인 문제가 발생
    //엔티티를 파라미터로 받으면 안됨
    //엔티티를 외부에 노출하면 안됨
    //API요청 스펙에 맞춰 별도의 DTO를 파라미터로 받아야한다
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    // 요청 값으로 Member 엔티티 대신에 별도의 DTO를 받는다
    // 엔티티의 값이 변경되도 별도의 클래스로 매핑시키는 형태기 때문에
    // 컴파일 오류로 쉽게 코드 변경이 가능하다
    //엔티티와 API 스펙을 명확하게 분리할 수 있다
    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {
        Member member = new Member();
        member.setName(request.getName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateMemberRequest request) {

        memberService.update(id, request.getName());
        Member findMember = memberService.findOne(id);

        return new UpdateMemberResponse(id, findMember.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @Data
    //DTO: 엔티티에 필요한 데이터를 위해 외부 값과 매핑시키는 클래스
    static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }
}
