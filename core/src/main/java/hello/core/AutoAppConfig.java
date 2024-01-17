package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
        // 스캔 시작 지점을 지정한다, 디폴트는 @ComponentScan가 붙어있는 클래스가 시작 지점
//        basePackages = "hello.core.member",
        // 제외 시킬 것
        // AppConfig는 수동으로 빈을 등록했기 때문에 충돌 방지를 위해 제외
        // 기존의 예제를 유지하기 위해, 실무에서는 이러지 않는다
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    /*@ComponentScan을 사용하면
     * Service, Repository 등등 의존관계 주입을 설정할 수 없다
     * 자동으로 스프링 빈으로 등록하는 @Component를 사용했기 때문이다
     * 의존관계 주입이 필요한 필드에 @Autowired 붙힌다*/

//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

    /*테스트 시에 오버라이딩이 되었다는 문구를 확인할 수 있다
    * 그러나 이런 설정들이 꼬이게 되면 찾기 힘들 버그를 만들어 낼수 있다
    * 스프링은 미연에 방지하고자 어플리케이션을 실행하면 에러를 보여주어
    * 설정 혹은 빈을 바꿀수 있게 권유한다
    *
    * 가능하면 애매한 상황을 만들지 않도록 기본의 흐름을 유지하는 개발을 해야한다
    * */
}
