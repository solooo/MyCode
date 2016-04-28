package solooo.mycode.file;

import java.io.IOException;
import java.nio.file.*;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/4/25
 * History:
 * his1:
 */
public class FileMonitor {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService ws = FileSystems.getDefault().newWatchService();
        Paths.get("D:\\").register(ws, StandardWatchEventKinds.ENTRY_CREATE);
        while(true) {
            WatchKey watchKey = ws.take();
            for (WatchEvent event: watchKey.pollEvents()) {
                System.out.println(event.context() + "发生了" + event.kind());
                System.out.println(Files.isReadable(Paths.get("D:\\" + event.context())));
            }
            if(!watchKey.reset()) {
                break;
            }
        }
    }
}
