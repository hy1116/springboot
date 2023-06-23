package com.hy.webTest.common.util;

import org.apache.commons.codec.binary.Base64;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

public class CryptoUtil {
    private static Logger logger = LoggerFactory.getLogger("CryptoUtil");
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

    // json value 복호화
    @SuppressWarnings("unchecked")
    public static JSONObject decryptJsonValue(JSONObject jo) {
        Iterator<String> it = jo.keys();
        while(it.hasNext()) {
            String key = it.next();
            Object obj = jo.get(key);
            logger.info("key [{}] obj [{}]",key,obj==null);

            if(obj instanceof JSONArray){
                JSONArray jsonArray = (JSONArray) obj;

                if(jsonArray.isNull(0)) continue;
                for(int i = 0;i<jsonArray.length();i++) {
                    JSONObject jsonObject = decryptJsonValue((JSONObject) jsonArray.get(i));
                    jo.put(key, jsonObject);
                }
                logger.debug("key[{}] is JSONArray : {}", key, jsonArray);
            } else if(obj instanceof JSONObject){
                JSONObject jsonObject = decryptJsonValue((JSONObject) obj);
                jo.put(key, jsonObject);
                logger.debug("key[{}] is JSONObject : {}", key, jsonObject);
            } else {
                if(!obj.toString().trim().isEmpty()){
                    String decryptedValue = "";
                    try{
                        decryptedValue = decryptAes(obj.toString());
                    } catch (Exception e){
                        decryptedValue = obj.toString();
                    }
                    logger.debug("decrypt value {}", decryptedValue);
                    jo.put(key, decryptedValue);
                }
            }
        }

        logger.info("{}",jo);

        return jo;
    }
}
