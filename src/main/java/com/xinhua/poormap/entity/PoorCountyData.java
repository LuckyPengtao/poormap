package com.xinhua.poormap.entity;

import lombok.Data;

@Data
public class PoorCountyData {
    private String province;
    private String cityName;
    /**
     * 纬度
     */
    private float lat;
    /**
     * 经度
     */
    private float lng;

    /**
     * 县区名
     */
    private String district;
    /**
     * 详细地址
     */
    private String address;
}
