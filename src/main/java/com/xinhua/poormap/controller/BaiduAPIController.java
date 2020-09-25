package com.xinhua.poormap.controller;

import com.alibaba.excel.EasyExcel;
import com.xinhua.poormap.easyexcel.data.read.PoorCounty;
import com.xinhua.poormap.easyexcel.listener.ExcelListener;
import com.xinhua.poormap.easyexcel.service.impl.DemoConsumerImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RestController
@RequestMapping("/baidu")
public class BaiduAPIController {

    @GetMapping("/geocoding")
    public void geocoding() throws FileNotFoundException {
        File file = new File("C:\\Users\\Administrator\\Desktop\\data.xls");
        EasyExcel.read(new FileInputStream(file), PoorCounty.class,new ExcelListener( p-> {
            DemoConsumerImpl consumer = new DemoConsumerImpl();
            consumer.excute(p);
        })).sheet().doRead();
    }
}
