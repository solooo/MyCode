package solooo.mycode.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/4/13.
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static String rootPath = "E:/opt/file/";

    @SuppressWarnings("resource")
    private void copyFile(FileInputStream sourceInputStream, File dest) throws IOException {

        try (FileChannel inputChannel = sourceInputStream.getChannel();
             FileChannel outputChannel = new FileOutputStream(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
    }

    /**
     * 创建excel文件
     * @param workbook
     */
    public static void saveToDisk(SXSSFWorkbook workbook) {

        if (!rootPath.endsWith(File.separator)) {
            rootPath += File.separator;
        }
        String fname = String.valueOf(System.currentTimeMillis()) + ".xlsx";
        String relPath = "export_excel" + File.separator + fname;
        String path = rootPath + relPath;
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            workbook.write(new FileOutputStream(file));
            System.out.println("-------------------------------------------");
            System.out.println("file name: " + file.getName());
            System.out.println("file length: " + file.length());
            System.out.println("file absolutePath: " + file.getAbsolutePath());
            System.out.println("-------------------------------------------");
            // 文件信息保存入库
//            FileUpload fileUpload = this.saveFileInfo(fileName, relPath);
//            return fileUpload.getId();
        } catch (IOException e) {
            logger.error("文件创建失败" + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
