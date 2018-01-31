package solooo.mycode.utils;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/4/13.
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    private static String rootPath = "E:/opt/file/";

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

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

    /**
     * 计算文件sha1
     * @param file
     * @return
     */
    public static String getSha1(File file) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance("SHA-1");
            FileInputStream in = new FileInputStream(file);
            FileChannel ch = in.getChannel();
            MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, ch.size());
            messagedigest.update(byteBuffer);
            return bufferToHex(messagedigest.digest());
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description 计算二进制数据
     * @return String
     * */
    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }
}
