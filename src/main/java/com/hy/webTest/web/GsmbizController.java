package com.hy.webTest.web;

import com.hy.webTest.common.util.CryptoUtil;
import com.hy.webTest.service.coupon.CouponRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // final 이나 @notNull 이 붙은 필드 생성자 자동생성 (lombok)
@RestController // controller + responseBody
public class GsmbizController {

    private final CouponRequestService couponRequestService;

    @PostMapping("/gsmbiz")
    public String gsmbiz(){
        return "gsmbiz";
    }

    @PostMapping("/gsmbiz/issue")
    public String gsmbizIssue() throws Exception {

        CouponRequestDto info = couponRequestService.getGsmbizRequestInfo();

        return "gsmbiz-issue";
    }
}
