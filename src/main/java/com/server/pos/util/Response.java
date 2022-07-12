package com.server.pos.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Response {
    private Integer status=200;
    private String message="Success";
    private Object object;
    private List<?> list;
    private Page<?>page;
}
