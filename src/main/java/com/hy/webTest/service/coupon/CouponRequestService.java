package com.hy.webTest.service.coupon;

import com.hy.webTest.common.util.HttpClientUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CouponRequestService {
    //private final CouponRepository couponRepository;

    public String getGsmbizIssueRequest() throws Exception {
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

        JSONObject jo = new JSONObject();
        jo.put("req_div_cd", "01");
        jo.put("issu_req_val", "00GS0109440600062323");
        jo.put("clico_issu_paym_no", "thanks_00000001");
        jo.put("clico_issu_paym_seq", "1");
        jo.put("cre_cnt", "1");
        jo.put("avl_div_cd", "02");

        String responseBody = HttpClientUtil.doHttpPostJson(url,headerMap,jo.toString());

        return responseBody;
    }


}
