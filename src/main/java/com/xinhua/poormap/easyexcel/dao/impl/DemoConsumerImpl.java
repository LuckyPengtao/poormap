package com.xinhua.poormap.easyexcel.dao.impl;

import com.xinhua.poormap.easyexcel.dao.ExcelConsumer;
import com.xinhua.poormap.easyexcel.data.read.PoorCounty;
import com.xinhua.poormap.util.BaiduAPI;

import java.util.List;

public class DemoConsumerImpl implements ExcelConsumer<PoorCounty> {
    @Override
    public void excute(List<PoorCounty> list) {
        for(PoorCounty data : list){
            String str = BaiduAPI.testPost(data.getLat(), data.getLng());
            System.out.println(str);
        }
    }
}
