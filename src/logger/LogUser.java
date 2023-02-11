package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static hasher.Hasher.hashString;

public class LogUser {
    public static void logTXT(String login, String id, String password, boolean isSuccess) throws IOException {
        String attempt;
        try (FileWriter myWriter = new FileWriter("log.txt", true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String output = "[%s] %s[%s]:%s [%s]\n";
            if (isSuccess) {
                attempt = "SUCCESS";
            }else {
                attempt = "FAIL";
            }
            myWriter.write(String.format(output, dtf.format(now), login, id, hashString(password), attempt));
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
