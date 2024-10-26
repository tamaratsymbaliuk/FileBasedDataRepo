package academyGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

public class DbBackup {
    private final String FILENAME;

    public DbBackup(String fileName) {
        FILENAME = fileName;
    }
    public void runEvery(int minutes, int seconds) {
        int milliseconds = minutes * 60 * 1000 + seconds * 1000; // converting minutes and sec in millisec
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Running backup every " + minutes + " minutes, " + seconds + " seconds");
                createNewVersion();
                removeOldVersion(5);
            }
        }, 0,milliseconds); // Run immediately and then every {minutes} minutes, {seconds} seconds
    }

    private void createNewVersion() {
        // try-with-resources
        try (FileInputStream inputStream = new FileInputStream(FILENAME);
            FileOutputStream outputStream = new FileOutputStream(createNewFileName())) {
            // When we pass a file name (as a string) to FileOutputStream, Java checks whether the file with that name already exists on the disk.
            //If the file does not exist, FileOutputStream creates a new file automatically.

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes old backup versions, keeping only the most recent `retainCount` versions.
     */
    private void removeOldVersion(int retainCount) {
        File directory = new File("."); // current directory
        File[] backupFiles = directory.listFiles((dir, name) -> name.startsWith(FILENAME) && name.contains("_"));

        if (backupFiles != null && backupFiles.length > retainCount) {
            // Sort files by last modified date in descending order (newest first)
            Arrays.sort(backupFiles, Comparator.comparingLong(File::lastModified).reversed());

            // Remove older files beyond retainCount
            for (int i = retainCount; i < backupFiles.length; i++) {
                if (backupFiles[i].delete()) {
                    System.out.println("Deleted old backup: " + backupFiles[i].getName());
                } else {
                    System.out.println("Failed to delete old backup: " + backupFiles[i].getName());
                }
            }
        }
    }
    private String createNewFileName() {
        LocalDateTime now = LocalDateTime.now();
        // Format timestamp as desired (e.g., "YYYY-MM-dd HH:mm:ss") 2024-10-26 02:13:05
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Convert timestamp to string
        String timestampString = now.format(formatter);
        return FILENAME + "_" + timestampString; //  db.file_2024-10-26  02:13:05

    }
}
