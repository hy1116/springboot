package com.hy.webTest.service.coupon;

import com.hy.webTest.common.util.CryptoUtil;
import com.hy.webTest.web.CouponRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CouponRequestService {
    //private final CouponRepository couponRepository;

    public CouponRequestDto getGsmbizRequestInfo() throws Exception {
        String url = "https://t-api.gsncoupon.com/gsmapi/coupon/issue";

        String site_id = "0706";
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("content-type", "application/json");
        headerMap.put("accept", "application/json");
        headerMap.put("accept-charset", "utf-8");
        headerMap.put("api_id", "IF-GSM-101");
        headerMap.put("auth_token", "Q+XrUC3s6DrvbjNXc5klJg==");
        headerMap.put("auth_cd ", site_id);
        headerMap.put("req_dt", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        headerMap.put("enc_yn ", "Y");

        Map<String,String> bodyMap = new HashMap<>();
        bodyMap.put("req_div_cd", "01");
        bodyMap.put("issu_req_val", "00GS0109440600062323");
        bodyMap.put("clico_issu_paym_no", "thanks_00000001");
        bodyMap.put("clico_issu_paym_seq", "1");
        bodyMap.put("cre_cnt", "1");
        bodyMap.put("avl_div_cd", "02");

        return new CouponRequestDto(url,headerMap,bodyMap);
    }


}
