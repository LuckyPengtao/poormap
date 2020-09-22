package com.xinhua.poormap.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

public class HttpUtil {

    public static String doGet(String url) {
        HttpClient client = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        int code = 0;
        try {
            code = client.executeMethod(getMethod);
            if (code == 200) {
                return getMethod.getResponseBodyAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
