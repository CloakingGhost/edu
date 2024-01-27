package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

//    EnumType.ORDINAL 이 기본값이다 상태값이 숫자로 기록 되기 때문에
//    STRING으로 변경해야 다른 enum 값이 들어올 때 에러를 방지할 수 있다
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; // READY, COMP

}
