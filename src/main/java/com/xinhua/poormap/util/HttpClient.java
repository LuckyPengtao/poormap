package com.xinhua.poormap.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpClient {
    /**
     * 向目的URL发送post请求
     *
     * @param url
     * @param params
     * @return
     */
    public static String doPost(String url, JSONObject params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);

        return response.getBody();
    }

    /**
     * 向目的URL发送get请求
     *
     * @param url     目的url
     * @param params  发送的参数
     * @param headers 发送的http头，可在外部设置好参数后传入
     * @return String
     */
    public static String doGet(String url, MultiValueMap<String, String> params, HttpHeaders headers) {
        RestTemplate client = new RestTemplate();
        HttpMethod method = HttpMethod.GET;
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = client.exchange(url, method, requestEntity, String.class);
        return response.getBody();
    }

}
