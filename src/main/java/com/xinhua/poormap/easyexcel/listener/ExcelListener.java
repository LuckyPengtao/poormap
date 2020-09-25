package com.xinhua.poormap.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xinhua.poormap.easyexcel.service.ExcelConsumer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    @Autowired
    ExcelConsumer consumer;

    private static final int BATCH_COUNT = 1000;
    List<T> list = new ArrayList<T>();

    public ExcelListener(ExcelConsumer consumer) {
        this.consumer = consumer;
    }

    public ExcelListener() {

    }

    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        list.add(t);
        if(list.size() > BATCH_COUNT){
            consumer.excute(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        consumer.excute(list);
    }
}
