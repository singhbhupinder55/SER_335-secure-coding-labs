// SER335 LAB6 TASK1
package edu.asu.ser335.jfm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JFMLogger {
    private static final String LOG_FILE = "jfmlog.txt";

    // SER335 LAB6 TASK1
    public static void logFailedLogin(String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] Failed login attempt - Username: %s, Password: %s, Role: %s",
                    timestamp, username, password, role);
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            // Optionally print stack trace for debugging
            e.printStackTrace();
        }
    }

    // SER335 LAB6 TASK1 - Log successful login
    public static void logSuccessfulLogin(String username, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = LocalDateTime.now().format(formatter);
            String logEntry = String.format("[%s] Successful login - Username: %s, Role: %s",
                    timestamp, username, role);
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
