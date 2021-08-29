package dunzo.assingment.utils;

import java.nio.file.Files;
import java.nio.file.Path;

public class Utils {
    public static String readFileAsString(Path fileName)throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(fileName));
        return data;
    }
}
