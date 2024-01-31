package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    @Description("상품주문")
    void 상품주문() {
        //given
        Member member = createMember(); // tip: ctrl + alt + m

        Book book = createBook("new Book", 10000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        // 상품 주문시 상태는 ORDER
        Assertions.assertThat(findOrder.getStatus())
                .isEqualTo(OrderStatus.ORDER);
        // 주문한 상품 종류 수가 정확해야 한다.
        Assertions.assertThat(findOrder.getOrderItems().size())
                .isEqualTo(1);
        // 주문 가격은 가격 * 수량
        Assertions.assertThat(10000 * orderCount)
                .isEqualTo(findOrder.getTotalPrice());
        // 주문 수량만큼 재고가 줄어야 한다.
        Assertions.assertThat(book.getStockQuantity())
                .isEqualTo(8);

    }

    @Test
    @Description("상품주문 재고수량 초가")
    void 상문주문_재고수량초과() {
        //given
        Member member = createMember();
        Item item = createBook("b1", 10000, 10);

        //when
        int count = 11;
        Assertions.assertThatThrownBy(() ->orderService.order(member.getId(),item.getId(), count))
                .isInstanceOf(NotEnoughStockException.class);

        //then
    }

    @Test
    @Description("주문취소")
    void 주문취소() {
        //given
        Member member = createMember();
        Item item = createBook("b2", 10000, 10);

        int orderCount = 3;

        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        //when
        orderService.cancelOrder(orderId);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        //주문 상태가 CANCEL이 되어야함
        Assertions.assertThat(findOrder.getStatus())
                .isEqualTo(OrderStatus.CANCEL);
        //재고가 원래 수량으로 돌아와야 함
        Assertions.assertThat(item.getStockQuantity())
                .isEqualTo(10);

    }



    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("m1");
        member.setAddress(new Address("seoul", "river", "123-123"));
        em.persist(member);
        return member;
    }

}