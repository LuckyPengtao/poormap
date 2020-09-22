package com.xinhua.poormap.easyexcel.data.read;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoorCounty {

    @ExcelProperty("省份")
    private String province;
    @ExcelProperty("城市")
    private String city;
    @ExcelProperty("维度")
    private float lat;
    @ExcelProperty("经度")
    private float lng;
}
