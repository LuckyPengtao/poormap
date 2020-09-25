package com.xinhua.poormap.easyexcel.service;

import java.util.List;

public interface ExcelConsumer<E> {
    void excute(List<E> list);
}
