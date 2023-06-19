package com.hy.webTest.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Getter // 선언된 모든 필드의 get 메소드를 생성해줌
@RequiredArgsConstructor // 성성된 모든 final 필드의 생성자를 생성해줌
public class CouponRequestDto {
    private final String url;
    private final Map<String,String> header;
    private final Map<String,String> body;
}
