package com.moa.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {
    // member_id
    private Long id;

    private String title;

    private String content;

//    private LocalDateTime postDate;
}
