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
        String jsonStr = "{\"coupsendlist\":[{\"key1\":\"rcLwW/lvIzniHfRAVLBNHw==\"},{\"key2\":\"9gfNYZcORpocZLUKPCZOEQ==\"}],"
            +"\"coupapprolist\":[null],"
            +"\"camp_id\":\"h164ssPl0qdyA4a8g6a7Vg==\","
            +"\"last_use_mcht_cd\":\"rcLwW/lvIzniHfRAVLBNHw==\","
            +"\"last_use_dt\":\"rcLwW/lvIzniHfRAVLBNHw==\","
            +"\"cupn_no\":\"9gfNYZcORpocZLUKPCZOEQ==\","
            +"\"prod_cd\":\"eVN2uhAmwzjL6UQJr6o2tQ==\","
            +"\"prod_nm\":\"CEluBO2VLLN9e0seliojvYMaoTcrtphIET9gltdBKwOAXIVPKBi+gYuvP48XAMxe\","
            +"\"mcht_nm\":\"rcLwW/lvIzniHfRAVLBNHw==\","
            +"\"use_yn\":\"9sSHDk/oJgE3FSffEr7fSQ==\","
            +"\"issu_cncl_yn\":\"9sSHDk/oJgE3FSffEr7fSQ==\","
            +"\"issu_cncl_dt\":\"rcLwW/lvIzniHfRAVLBNHw==\","
            +"\"avl_start_dy\":\"0h9Rxg29tujyS4jzgVuzpQ==\","
            +"\"avl_end_dy\":\"THXqJ3lOJRhnlGaGWxzSSg==\","
            +"\"cupn_ramt\":\"l5ak90aWWbFk3mDZ+C1/6Q==\","
            +"\"state\":\"GtWT4NEFeJ4ZQvJm15JoJw==\","
            +"\"famt_amt\":\"l5ak90aWWbFk3mDZ+C1/6Q==\","
            +"\"tot_usable_cnt\":\"8fViMSL5YjvKm7XZWspEnw==\","
            +"\"use_unit_amt\":\"OXpjithoHTWLJEyQjBq/HQ==\","
            +"\"use_cnt\":\"OXpjithoHTWLJEyQjBq/HQ==\","
            +"\"use_amt\":\"OXpjithoHTWLJEyQjBq/HQ==\","
            +"\"issu_dt\":\"HHkr5LzxVWF/aL4C3/cBcQ==\","
            +"\"headers\" : {\"res_cd\":\"00000\",\"res_msg\":\"error\"}}";

        JSONObject json = (JSONObject) new JSONTokener(jsonStr).nextValue();
        JSONObject decryptedJson = CryptoUtil.decryptJsonValue(json);
        String res_cd = ((JSONObject)decryptedJson.get("headers")).get("res_cd").toString();

        assertThat(res_cd).isEqualTo("00000");
    }

}
