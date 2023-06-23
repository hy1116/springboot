package com.hy.webTest.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class HttpClientUtil {
    private final static int connectionTimeout 	= 10000;
    private final static int soTimeout 			= 10000;

    private static HttpClient getClient(){
        // Timeout
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(connectionTimeout)
                .setConnectionRequestTimeout(connectionTimeout)
                .setSocketTimeout(soTimeout)
                .build();

        // TLS
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                SSLContexts.createDefault(),
                new String[] { "TLSv1.1", "TLSv1.2" },
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());

        // 클라이언트 생성 및 Timeout & TLS 설정 적용
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultRequestConfig(config)
                .setSSLSocketFactory(sslsf)
                .build();

        return client;
    }

    public static synchronized String doHttpGet(String _url) throws Exception{
        HttpClient client = getClient();

        // URL 및 method 설정
        HttpGet method = new HttpGet(_url);

        String responseBody;
        try {
            // Request 요청
            HttpResponse response = client.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                HttpEntity entity = response.getEntity();
                //인코딩 방식에 따른 한글 깨짐 방지
                InputStream resStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp;
                while((resTemp = br.readLine()) != null){
                    resBuffer.append(resTemp);
                }
                responseBody = resBuffer.toString();
            }else{
                throw new Exception("HTTPCLIENT ERROR : " + response.getStatusLine());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return responseBody;
    }

    public static synchronized String doHttpPostJson(String _url , String _data) throws Exception{
        HttpClient client = getClient();

        // URL 및 method 설정
        HttpPost method = new HttpPost(_url);

        // request 파라미터 셋팅
        StringEntity reqEntity = new StringEntity(_data);
        method.setEntity(reqEntity);

        String responseBody;
        try {
            // Request 요청
            HttpResponse response = client.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                HttpEntity entity = response.getEntity();
                //인코딩 방식에 따른 한글 깨짐 방지
                InputStream resStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp;
                while((resTemp = br.readLine()) != null){
                    resBuffer.append(resTemp);
                }
                responseBody = resBuffer.toString();
            }else{
                throw new Exception("HTTPCLIENT ERROR : " + response.getStatusLine());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return responseBody;
    }

    public static synchronized String doHttpPostJson(String _url, Map<String,String> _heads , String _data) throws Exception{
        HttpClient client = getClient();

        // URL 및 method 설정
        HttpPost method = new HttpPost(_url);

        // 헤더 추가정보 셋팅
        for(String h : _heads.keySet()) method.setHeader(h,_heads.get(h));
        System.out.println("Headers : "+ Arrays.toString(method.getAllHeaders()));

        // request 파라미터 셋팅
        StringEntity reqEntity = new StringEntity(_data);
        method.setEntity(reqEntity);

        String responseBody;
        try {
            // Request 요청
            HttpResponse response = client.execute(method);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK || statusCode == HttpStatus.SC_CREATED) {
                HttpEntity entity = response.getEntity();
                //인코딩 방식에 따른 한글 깨짐 방지
                InputStream resStream = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp;
                while((resTemp = br.readLine()) != null){
                    resBuffer.append(resTemp);
                }
                responseBody = resBuffer.toString();
            }else{
                throw new Exception("HTTPCLIENT ERROR : " + response.getStatusLine());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            method.releaseConnection();
        }
        return responseBody;
    }
}
