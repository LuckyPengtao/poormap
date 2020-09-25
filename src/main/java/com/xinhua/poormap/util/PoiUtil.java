package com.xinhua.poormap.util;

import com.xinhua.poormap.entity.PoorCountyData;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PoiUtil {

    /**
     *  Excel解析成员工数据集合
     */
    public static List<PoorCountyData> excel2Data(){
        String filePath = "C:\\Users\\pengtao\\Documents\\poor.xls";
        List<PoorCountyData> list = new ArrayList<>();
        PoorCountyData data = null;
        try {
            //1. 创建一个 workbook 对象
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filePath));
            //2. 获取 workbook 中表单的数量
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int i = 0; i < numberOfSheets; i++) {
                //3. 获取表单
                HSSFSheet sheet = workbook.getSheetAt(i);
                //4. 获取表单中的行数
                int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
                for (int j = 0; j < physicalNumberOfRows; j++) {
                    //5. 跳过标题行
                    if (j == 0) {
                        continue;//跳过标题行
                    }
                    //6. 获取行
                    HSSFRow row = sheet.getRow(j);
                    if (row == null) {
                        continue;//防止数据中间有空行
                    }
                    //7. 获取列数
                    int physicalNumberOfCells = row.getLastCellNum();
                    data = new PoorCountyData();
                    for (int k = 0; k < physicalNumberOfCells; k++) {
                        HSSFCell cell = row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        switch (cell.getCellTypeEnum()) {
                            case STRING:
                                String cellValue = cell.getStringCellValue();
                                switch (k) {
                                    case 0:
                                        data.setProvince(cellValue);
                                        break;
                                    case 1:
                                        data.setCityName(cellValue);
                                        break;
                                }
                                break;
                            default: {
                            }
                            break;
                        }
                    }
                    list.add(data);
                }
            }
            } catch (IOException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void data2Excel(List<PoorCountyData> list) {
        //1. 创建一个 Excel 文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //2. 创建文档摘要
        workbook.createInformationProperties();
        //3. 获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //文档类别
        docInfo.setCategory("员工信息");
        //文档管理员
        docInfo.setManager("xpt");
        //设置公司信息
        docInfo.setCompany("www.baidu.com");
        //4. 获取文档摘要信息
        SummaryInformation summaryInfo = workbook.getSummaryInformation();
        //文档标题
        summaryInfo.setTitle("贫困县经纬度_20200916");
        //文档作者
        summaryInfo.setAuthor("xpt");
        // 文档备注
        summaryInfo.setComments("本文档由 xpt 提供");
        //5. 创建样式
        //创建标题行的样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
        HSSFSheet sheet = workbook.createSheet("贫困县经纬度");
        //设置列的宽度
        sheet.setColumnWidth(0, 10 * 256);
        sheet.setColumnWidth(1, 12 * 256);
        sheet.setColumnWidth(2, 10 * 256);
        sheet.setColumnWidth(3, 5 * 256);
        sheet.setColumnWidth(4, 12 * 256);
        sheet.setColumnWidth(5, 24 * 256);
        //6. 创建标题行
        HSSFRow r0 = sheet.createRow(0);

        HSSFCell c0 = r0.createCell(0);
        c0.setCellValue("province");
        c0.setCellStyle(headerStyle);
        HSSFCell c1 = r0.createCell(1);
        c1.setCellStyle(headerStyle);
        c1.setCellValue("city_name");
        HSSFCell c2 = r0.createCell(2);
        c2.setCellStyle(headerStyle);
        c2.setCellValue("lat");
        HSSFCell c3 = r0.createCell(3);
        c3.setCellStyle(headerStyle);
        c3.setCellValue("lng");
        HSSFCell c4 = r0.createCell(4);
        c4.setCellStyle(headerStyle);
        c4.setCellValue("district");
        HSSFCell c5 = r0.createCell(5);
        c5.setCellStyle(headerStyle);
        c5.setCellValue("address");
        for (int i = 0; i < list.size(); i++) {
            PoorCountyData data = list.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(data.getProvince());
            row.createCell(1).setCellValue(data.getCityName());
            row.createCell(2).setCellValue(data.getLat());
            row.createCell(3).setCellValue(data.getLng());
            row.createCell(4).setCellValue(data.getDistrict());
            row.createCell(5).setCellValue(data.getAddress());
        }
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        FileOutputStream fileOutputStream = null;
        try {
            headers.setContentDispositionFormData("attachment", new String("贫困县经纬度_20200916.xls".getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(output);
            fileOutputStream = new FileOutputStream("C:\\Users\\pengtao\\Documents\\贫困县经纬度_20200916.xls");
            fileOutputStream.write(output.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return new ResponseEntity<byte[]>(output.toByteArray(), headers, HttpStatus.CREATED);
    }

}
