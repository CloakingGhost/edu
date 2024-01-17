package hello.core.lombok;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class HelloLombokTest {
    @Test
    void lombokTest() {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("specialName");

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println("helloLombok = " + helloLombok);

        Assertions.assertThat(name).isEqualTo("specialName");
    }
}