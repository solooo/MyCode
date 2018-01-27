package solooo.mycode.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:Eric
 * Date:2018/1/27
 */
public class WriteExcelUtilsTest {
    @Test
    public void exportExcel() throws Exception {
        List<Map<String, Integer>> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Map<String, Integer> map = new HashMap<>();
            map.put("index", i);
            datas.add(map);
        }
        // 创建标题
        LinkedHashMap<String, String> titleMap = new LinkedHashMap<>();
        titleMap.put("index", "序号");

        SXSSFWorkbook workbook = WriteExcelUtils.exportExcel(datas, titleMap);
        FileUtils.createExcel(workbook, null);
    }

}