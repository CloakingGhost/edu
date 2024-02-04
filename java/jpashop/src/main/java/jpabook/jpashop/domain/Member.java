package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Embedded
    private Address address;

//  연관관계의 주인 member
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();



}
