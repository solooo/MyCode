package solooo.mycode.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Description:
 * Author:Eric
 * Date:2018/1/31
 */
public class FileUtilsTest {

    @Test
    public void getSha1() throws IOException, NoSuchAlgorithmException {
        String sha1 = FileUtils.getSha1(new File("E:\\2017年经信局对上争取项目汇总表-模板.xls"));
//        System.out.println(sha1);
//        Assert.assertEquals("0e753a71a26c63c13dae59e7466ad3474600ae00", sha1);
        String sha12 = FileUtils.getSha1(new File("C:\\Users\\Administrator\\Desktop\\2017年经信局对上争取项目汇总表.xls"));
//        System.out.println(sha1);
        Assert.assertEquals(sha12, sha1);
    }
}