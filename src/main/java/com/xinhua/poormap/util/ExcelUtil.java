package com.xinhua.poormap.util;

import com.alibaba.excel.EasyExcel;
import com.xinhua.poormap.easyexcel.listener.ExcelListener;

import java.io.InputStream;

public class ExcelUtil {
    /**
     * EasyExcel读文件
     *
     * @param stream
     * @param c
     */
    public static void read(InputStream stream, Class<?> c) {
        // 这里默认读取第一个sheet
        EasyExcel.read(stream, c, new ExcelListener()).sheet().doRead();
    }
}
