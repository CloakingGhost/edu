package com.moa.controller;

import com.moa.domain.RoleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private Long id;
    private String name;
    private RoleStatus role;
//    private String pw;
}
