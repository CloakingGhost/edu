package hello.core;

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
    * 따라서, 의존관계 주입이 필요한 필드에 @Autowired 붙힌다*/
}
