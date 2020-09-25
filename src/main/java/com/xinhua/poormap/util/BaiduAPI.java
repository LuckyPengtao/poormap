package com.xinhua.poormap.util;

import org.springframework.http.HttpHeaders;

public class BaiduAPI {

    private static String ak = "Ny3rv1dCUSBbnvc7YNfnnTAwgEeiTS1I";

    /**
     * 逆地理编码
     *
     * @param lat 纬度
     * @param lng 经度
     * @return
     */
    public static String reverse_geocoding(float lat, float lng) {
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + ak +
                "&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        HttpHeaders headers = new HttpHeaders();
        return HttpClient.doGet(url, null, headers);
    }

    /**
     * 地理编码
     *
     * @param address 待解析的地址
     * @return
     */
    public static String geocoding(String address) {
        String url = "http://api.map.baidu.com/geocoding/v3/?ak=" + ak +
                "&address=" + address + "&output=json";
        HttpHeaders headers = new HttpHeaders();
        return HttpClient.doGet(url,null, headers);
    }

}
