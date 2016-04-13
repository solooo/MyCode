package solooo.mycode.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/4/13.
 */
public class FileUtils {

    @SuppressWarnings("resource")
    private void copyFile(FileInputStream sourceInputStream, File dest) throws IOException {

        try (FileChannel inputChannel = sourceInputStream.getChannel();
             FileChannel outputChannel = new FileOutputStream(dest).getChannel()) {
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        }
    }
}
