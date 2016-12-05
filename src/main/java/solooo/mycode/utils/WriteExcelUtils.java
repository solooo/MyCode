package solooo.mycode.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:16/11/29-029
 * History:
 * his1:
 */
public class WriteExcelUtils {


    public void writeExcel(String sheetName, String[] title, String[] fields, List<Map<String, Object>> data,
            OutputStream outputStream) {
        if (fields == null || fields.length == 0) {
            return;
        }
        if (data == null || data.size() == 0) {
            return;
        }
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(StringUtils.isEmpty(sheetName) ? "报表" : sheetName);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row;
        int index = 0;
        HSSFCell cell;
        if (title != null && title.length != 0) {
            row = sheet.createRow(0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
            for (int i = 0; i < title.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(title[i]);
            }
            index = 1;
        }

        int size = data.size();
        int[] widths=new int[title.length];
        String dataString;
        for (int i = 0; i < size; i++) {
            row = sheet.createRow(i + index);
            for (int j = 0; j < fields.length; j++) {
                dataString = String.valueOf(data.get(i).get(fields[j]));
                widths[j]=Math.max(widths[j], dataString.getBytes().length);
                row.createCell(j).setCellValue(dataString);
            }
        }
        for (int i = 0; i < title.length; i++) {
            sheet.setColumnWidth(i, Math.max(widths[i],20)*256);
            //sheet.autoSizeColumn(i);
        }
        try {
            wb.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        /*String sheetName = "sheet0";
        String title[] = {"名称","别名","描述"};
        String fields[] = {"name","description","alias"};
        try {
            OutputStream out = new FileOutputStream("D:/test.xls");
            List<Map<String, Object>> data = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();
            map.put("name","name1");
            map.put("alias","alias1");
            map.put("description","description1");
            data.add(map);
            map = new HashMap<>();
            map.put("name","name2");
            map.put("alias","alias2");
            map.put("description","description2");
            data.add(map);
            new WriteExcelUtils().writeExcel(sheetName, title, fields, data, out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        SXSSFWorkbook wb = new SXSSFWorkbook(-1); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet();
        for(int rownum = 0; rownum < 1000; rownum++){
            Row row = sh.createRow(rownum);
            for(int cellnum = 0; cellnum < 10; cellnum++){
                Cell cell = row.createCell(cellnum);
                String address = new CellReference(cell).formatAsString();
                cell.setCellValue(address);
            }

        }

        FileOutputStream out = new FileOutputStream("D:/sxssf.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
}
