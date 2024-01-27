package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime localDateTime; // 주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // ORDER, CANCEL

    //== 연관관계 편의 메서드 ==//
    //== 관리를 하는 쪽에 작성한다 ==//
    //== 양방향에 사용 ==//
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this); // 메서드 하나로 주문목록에 한번에 추가한다
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this); // 메서드 하나로 주문정보를 지정한다
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this); // 메서드 하나로 주문정보를 지정한다
    }





}
