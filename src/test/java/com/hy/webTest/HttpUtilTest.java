package com.hy.webTest;

import com.hy.webTest.common.util.HttpClientUtil;

import org.junit.jupiter.api.Test;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.*;

public class HttpUtilTest {
    String testUrl = "google.com";

    @Test
    public void doHttpGet_test() throws Exception {
        String resStr = HttpClientUtil.doHttpGet(testUrl);
        assertThat(resStr).isNotNull();
    }
}
