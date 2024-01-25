package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloTest {
    @Test
    void hello() {
        Hello hello = new Hello();
        String name = hello.getName();
        Assertions.assertThat(name).isEqualTo(null);
    }
}
