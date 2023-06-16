package com.hy.webTest;

import com.hy.webTest.common.util.CryptoUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CryptoUtilTest {

    @Test
    public void encrypt_seed128_base64_test() throws Exception {
        // given
        String str = "0";
        // when
        String encStr = CryptoUtil.encryptSeed(str);
        // then
        assertThat(encStr).isEqualTo("lMrXpdLIZ8pJxq7+BTptDg==");
    }

    @Test
    public void encrypt_aes256_base64_test() throws Exception {
        // given
        String str = "0";
        // when
        String encStr = CryptoUtil.encryptAes(str);
        // then
        assertThat(encStr).isEqualTo("OXpjithoHTWLJEyQjBq/HQ==");
    }

}
