package com.xinhua.poormap.util;

import com.alibaba.fastjson.JSONObject;
import com.xinhua.poormap.entity.PoorCountyData;

import java.io.IOException;
import java.util.List;

public class BaiduAPI {
    private static String ak = "Ny3rv1dCUSBbnvc7YNfnnTAwgEeiTS1I";

    public static String testPost(float lat, float lng) {
        String url = "http://api.map.baidu.com/reverse_geocoding/v3/?ak=" + ak +
                "&output=json&coordtype=wgs84ll&location=" + lat + "," + lng;
        return HttpUtil.doGet(url);

    }

    public static void main(String[] args) throws Exception {
        List<PoorCountyData> list = PoiUtil.excel2Data();
        for(PoorCountyData data : list){
            String str = testPost(data.getLat(), data.getLng());
            JSONObject object = JSONObject.parseObject(str);
            JSONObject result = (JSONObject) object.get("result");
            JSONObject addressComponent = (JSONObject) result.get("addressComponent");
            String district = addressComponent.getString("district");
            String address = addressComponent.getString("province") + addressComponent.getString("city") + addressComponent.getString("district");
            data.setDistrict(district);
            data.setAddress(address);
        }
        PoiUtil.data2Excel(list);
    }


}
