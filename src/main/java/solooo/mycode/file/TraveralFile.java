package solooo.mycode.file;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * Created by Administrator on 2016/4/19.
 */
public class TraveralFile {

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Paths.get("D:\\Station\\D\\PSDVR\\"), EnumSet.of(FileVisitOption.FOLLOW_LINKS), Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                    throws IOException {

                System.out.println("visitFile: " + file.getFileName() + ", " + file.getParent().toAbsolutePath() + "," + file.getParent().getFileName());
                String oldPath = file.getParent().toAbsolutePath() + "\\old\\";
                Files.createDirectories(Paths.get(oldPath));
                Files.move(file, Paths.get(oldPath + file.getFileName()));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e)
                    throws IOException {
                if (e == null) {
                    System.out.println("postVisitDirectory: " + dir);
                    return FileVisitResult.CONTINUE;
                } else {
                    // directory iteration failed
                    throw e;
                }
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                System.out.println("preVisitDirectory:" + dir.getFileName());
                if ("old".equals(dir.getFileName().toString())) {
                    return FileVisitResult.SKIP_SIBLINGS;
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
