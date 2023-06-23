package com.hy.webTest;

import com.hy.webTest.common.util.CryptoUtil;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.*;

public class CryptoUtilTest {
    private static Logger logger = LoggerFactory.getLogger(CryptoUtilTest.class);
    String baseEncStr = "0";
    String baseAesDecStr = "OXpjithoHTWLJEyQjBq/HQ==";
    String baseSeedDecStr = "lMrXpdLIZ8pJxq7+BTptDg==";

    @Test
    public void encrypt_seed128_base64_base_test() throws Exception {
        assertThat(CryptoUtil.encryptSeed(baseEncStr)).isEqualTo(baseSeedDecStr);
    }

    @Test
    public void decrypt_seed128_base64_base_test() throws Exception {
        String decStr =  CryptoUtil.decryptSeed(baseSeedDecStr);
        logger.info("decStr: {}",decStr);
        assertThat(decStr).isEqualTo(baseEncStr);
    }

    @Test
    public void encrypt_aes256_base64_base_test() throws Exception {
        assertThat(CryptoUtil.encryptAes(baseEncStr)).isEqualTo(baseAesDecStr);
    }

    @Test
    public void decrypt_aes256_base64_base_test() throws Exception {
        String decStr = CryptoUtil.decryptAes(baseAesDecStr);
        logger.info("decStr: {}",decStr);
        assertThat(decStr).isEqualTo(baseEncStr);
    }

    @Test
    public void decrypt_json_test() throws Exception {
        String jsonStr = "{\"key1\":\"val1\"}";
        JSONObject json = (JSONObject) new JSONTokener(jsonStr).nextValue();
        JSONObject decryptedJson = CryptoUtil.decryptJsonValue(json);
        String res_cd = ((JSONObject)decryptedJson.get("headers")).get("res_cd").toString();

        assertThat(res_cd).isEqualTo("00000");
    }

}
