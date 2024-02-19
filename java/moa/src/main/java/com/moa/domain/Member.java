package com.moa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter

public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Setter
    private String name;

    @Setter
    @Enumerated(EnumType.STRING)
    private RoleStatus role; // ADMIN, USER

    // 기본생성자 사용 금지
    protected Member(){

    }
    public Member(String name, RoleStatus role){
        this.name = name;
        this.role = role;
    }

}
