package com.xinhua.poormap.easyexcel.dao;

import java.util.List;

public interface ExcelConsumer<E> {
    void excute(List<E> list);
}
