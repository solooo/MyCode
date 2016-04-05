package solooo.mycode.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * Title:Excel读取工具类
 * Description:Excel读取工具类
 * Copyright:Copyright 2006 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016年3月31日
 * History:
 * his1:
 */
public class ReadExcelUtils {

    /**
     * Excel文件
     */
    private Workbook workbook;

    /**
     * 标题存值Map
     */
    private Map<Integer, String> titleMap = new HashMap<>();
    
    /**
     * 
     */
    private List<Map<String, Object>> dataMapList = new ArrayList<>();
    
    /**
     * 总行数
     */
    private int totalRows = 0;
    
    /**
     * 总列数
     */
    private int totalColumns = 0;
    
    /**
     * 模板中标题所在行
     */
    private int titleRowNum = 1;
    
    /**
     * 接收File对象构造函数
     * @param file
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ReadExcelUtils(File file) throws InvalidFormatException, IOException {
        this.workbook = WorkbookFactory.create(file);
        
    }
    
    /**
     * 流对象构造
     * @param is
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ReadExcelUtils(InputStream is) throws InvalidFormatException, IOException {
        this.workbook = WorkbookFactory.create(is);
        
    }
    
    /**
     * 读取文件第一个sheet内容
     * @author PeiJian
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getContentList(){
        Sheet sheet = this.workbook.getSheetAt(0);
        return getTitleAndContent(sheet);
    }
    
    /**
     * 根据sheet index读取内容
     * @author PeiJian
     * @param sheetAt 第几页
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getContentList(int sheetAt){
        Sheet sheet = this.workbook.getSheetAt(sheetAt);
        return getTitleAndContent(sheet);
    }

    /**
     * 根据sheet名称读取内容
     * @author PeiJian
     * @param sheetName 页名
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getContentList(String sheetName) {
        Sheet sheet = this.workbook.getSheet(sheetName);
        return getTitleAndContent(sheet);
    }
    
    /**
     * 获取表格内容
     * <br>以[标题:值]存储为Map
     * @author PeiJian
     * @param sheet 页对象
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getTitleAndContent(Sheet sheet) {
        totalRows = sheet.getLastRowNum();
        totalColumns = sheet.getRow(titleRowNum).getLastCellNum();
        for (int i = titleRowNum + 1; i <= totalRows; i++) {
            Map<String, Object> map = new HashMap<>();
            for (int j = 0; j < totalColumns; j++) {
                String title = this.getTitle(sheet, j);
                Object content = getCellContent(sheet, i, j);
                map.put(title, content);
            }
            dataMapList.add(map);
        }
        return dataMapList;
    }
    
    /**
     * 获取单元格对象
     * @author PeiJian
     * @param sheet 页
     * @param row 行
     * @param column 列
     * @return Object
     */
    private Object getCellContent(Sheet sheet, int row, int column) {
        
        Object content = null;
        DecimalFormat df = new DecimalFormat("0");
        Cell cell = sheet.getRow(row).getCell(column);
        if (cell == null) {
            return content;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) { 
                content = cell.getDateCellValue(); // 日期类型
            } else { 
                content = df.format(cell.getNumericCellValue()); // 普通数字
            }
        } else {
            content = cell.getStringCellValue();
        }
        return content;
    }
    
    /**
     * 获取列标题
     * 每列第二行
     * @author PeiJian
     * @param sheet 页
     * @param column 列
     * @return String
     */
    private String getTitle(Sheet sheet, int column) {
        String title = titleMap.get(column);
        if (StringUtils.isBlank(title)) {
            title = String.valueOf(this.getCellContent(sheet, titleRowNum, column));
        }
        return title;
    }
}
