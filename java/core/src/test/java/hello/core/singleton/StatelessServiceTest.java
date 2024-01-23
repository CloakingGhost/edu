package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatelessServiceTest {
    @Test
    void statelessServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatelessService statelessService1 = ac.getBean(StatelessService.class);
        StatelessService statelessService2 = ac.getBean(StatelessService.class);

        //ThreadA: A사용자 10000원 주문
        int price1 = statelessService1.order("userA", 10000);
        //Threadb: B사용자 20000원 주문
        int price2 = statelessService2.order("userB", 20000);

        //ThreadA: A사용자 주문 금액 조회
        System.out.println("price1 = " + price1);

        assertThat(price1).isEqualTo(10000);

    }

    static class TestConfig {

        @Bean
        public StatelessService statelessService() {
            return new StatelessService();
        }
    }

}