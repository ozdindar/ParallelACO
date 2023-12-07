package core.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static synchronized void writeToFile(String fileName, String text, boolean append)
    {
        try {
            FileWriter writer = new FileWriter(fileName,append);

            writer.write(text);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
