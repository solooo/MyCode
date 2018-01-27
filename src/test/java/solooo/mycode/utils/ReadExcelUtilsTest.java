package solooo.mycode.utils;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Author:Eric
 * Date:2018/1/26
 */
public class ReadExcelUtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void getContentList() throws Exception {
        ReadExcelUtils readExcelUtils = new ReadExcelUtils(new File("E:\\资金项目汇总表.xls"));
        List<Map<String, Object>> contentList = readExcelUtils.getTitleAndContent();
        for (Map<String, Object> map : contentList) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.print(entry.getValue() + "|");
            }
            System.out.println("");
        }
    }

    @Test
    public void getContentList1() throws Exception {
        ReadExcelUtils readExcelUtils = new ReadExcelUtils(new File("E:\\2017年经信局对上争取项目汇总表-模板.xls"));
        List<List<Object>> contentList = readExcelUtils.getContent();
        for (List<Object> objects : contentList) {
            for (int i = 0; i < objects.size(); i++) {
                System.out.println(i + " " + objects.get(i));
            }
            System.out.println("-------------");

        }
    }

}