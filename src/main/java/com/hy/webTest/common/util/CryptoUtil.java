package com.hy.webTest.common.util;

import com.hy.webTest.common.util.KISA_SEED_CBC;
import com.hy.webTest.web.CouponRequestDto;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.transaction.Transactional;
import java.util.Map;
import java.util.function.Function;

public class CryptoUtil {
    // AES-256/SEED128
    private static String alg = "AES/CBC/PKCS5Padding";
    private static String key = "1234567890ABCDEFGHIJKLMNOPQRSTUV";
    private static String IV  = "1234567890ABCDEF";

    public static String encryptSeed(String str) throws Exception {
        byte[] strBytes = str.getBytes("UTF-8");
        byte[] encrypted = KISA_SEED_CBC.SEED_CBC_Encrypt(key.getBytes(),IV.getBytes(),strBytes,0,strBytes.length);
        return new String(Base64.encodeBase64(encrypted), "UTF-8");
    }

    public static String decryptSeed(String str) throws Exception {
        byte[] strBytes = Base64.decodeBase64(str.getBytes());
        byte[] enc = KISA_SEED_CBC.SEED_CBC_Decrypt(key.getBytes(), IV.getBytes(), strBytes, 0, strBytes.length);
        return new String(enc, "UTF-8");
    }

    public static String encryptAes(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

        byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
        return new String(Base64.encodeBase64(encrypted),"UTF-8");
    }

    public static String decryptAes(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(alg);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParamSpec = new IvParameterSpec(IV.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

        byte[] decodedBytes = Base64.decodeBase64(str);
        byte[] decrypted = cipher.doFinal(decodedBytes);
        return new String(decrypted, "UTF-8");
    }

    public static Map<String,String> encryptMapValues(Map<String, String> map) throws Exception{
        for(Map.Entry<String,String> e : map.entrySet()){
            e.setValue(encryptAes(e.getValue()));
        }
        return map;
    }
}
