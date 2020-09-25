package com.xinhua.poormap.easyexcel.service.impl;

import com.xinhua.poormap.easyexcel.service.ExcelConsumer;
import com.xinhua.poormap.easyexcel.data.read.PoorCounty;
import com.xinhua.poormap.util.BaiduAPI;

import java.util.List;

public class DemoConsumerImpl implements ExcelConsumer<PoorCounty> {
    @Override
    public void excute(List<PoorCounty> list) {
        if(list == null || list.size() == 0)
            return;
        for(PoorCounty data : list){
            String str = BaiduAPI.reverse_geocoding(data.getLat(), data.getLng());
            System.out.println(str);
        }
    }
}
