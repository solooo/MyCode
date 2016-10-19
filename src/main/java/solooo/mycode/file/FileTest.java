package solooo.mycode.file;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/16
 * History:
 * his1:
 */
public class FileTest {

    public static void main(String[] args) {
        Path path = Paths.get("D:\\Station\\D\\PSDVR\\2015\\01\\84123078\\00100003_160506_161130.jpg");
        Path to = path.resolve("old");
        System.out.println(to.toString());
    }
}
