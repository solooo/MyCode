package solooo.mycode.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: Excel导出工具类
 * Author:Eric
 * Date:2017/12/5
 */
public class WriteExcelUtils {
    /**
     * 创建Excel
     * @param dataList 导出数据
     * @param titleMap 标题头
     * @return
     */
   public static SXSSFWorkbook exportExcel(List<?> dataList, LinkedHashMap<String, String> titleMap) {
        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet();
        sheet.trackAllColumnsForAutoSizing();
        List<String> list = new ArrayList<>();

        Row row = sheet.createRow(0);

        // 赋表头
        int m = 0;
        for (Map.Entry<String, String> entry : titleMap.entrySet()) {
            Cell cell = row.createCell(m);
            cell.setCellValue(entry.getValue());
            list.add(entry.getKey());
            m++;
            //
            CellStyle cellStyle = getCellStyle(wb);
            Font font = wb.createFont();
            font.setBold(true);
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
        }

        CellStyle cellStyle = getCellStyle(wb);
        List<Map<String, Object>> importData = convert(dataList);
        /* 赋值 */
        for (int i = 0; i < importData.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < list.size(); j++) {
                String key = list.get(j);
                Cell cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                if ("num".equals(key)) {
                    cell.setCellValue(i + 1);
                } else {
                    Map<String, Object> map = importData.get(i);
                    Object obj = map.get(key);
                    if (obj == null) {
                        obj = "";
                    }
                    if ("createTime".equals(key)) {
                        sheet.autoSizeColumn(j);
                        Date d = new Date((Long) obj);
                        obj = DateUtils.toString(d, DateUtils.YYYY_MM_DD_HH_MM_SS);
                    }
                    if ("sex".equals(key)) {
                        obj = (Integer) obj == 0 ? "女" : "男";
                    }
                    cell.setCellValue(obj.toString());
                }
            }
        }

        return wb;
    }

    private static CellStyle getCellStyle(SXSSFWorkbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);// 下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        return cellStyle;
    }

    /**
     * List<Object> 转 List<Map>
     * @param objs
     * @return
     */
   private static List<Map<String, Object>> convert(List<?> objs) {
       List<Map<String, Object>> dataList = new ArrayList<>();
       for (Object obj : objs) {
           String sr = JSONObject.toJSONString(obj);
           Map<String, Object> data = JSON
                   .parseObject(sr, new TypeReference<Map<String, Object>>(){});
           dataList.add(data);
       }
       return dataList;
   }
}
