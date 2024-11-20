package academyGroup;

import academyGroup.Repository.FileUtil;

import java.io.*;
import com.google.gson.Gson;

public class WriteAheadLog<T> {
    // Write-ahead log
    private final String FILENAME;

    public WriteAheadLog(String fileName) {
        this.FILENAME = fileName;
        FileUtil.createFileIfNew(FILENAME);
    }

    public void logAdd(String id, String className, T entity) {
        WriteAheadLogEntry<T> entry = new WriteAheadLogEntry<>(OperationType.ADD, className, entity, id);
        Gson gson = new Gson();
        String entryJson = gson.toJson(entry);
        saveToFile(entryJson);
    }

    public void logUpdate(String className, T entity) {
        WriteAheadLogEntry<T> entry = new WriteAheadLogEntry<>(OperationType.UPDATE, className, entity);
        Gson gson = new Gson();
        String entryJson = gson.toJson(entry);
        saveToFile(entryJson);
    }

    public void logRemove(String className, String id) {
        WriteAheadLogEntry<T> entry = new WriteAheadLogEntry<>(OperationType.REMOVE, className, id);
        Gson gson = new Gson();
        String entryJson = gson.toJson(entry);
        saveToFile(entryJson);
    }

    private void saveToFile(String entryJson){
        try (FileWriter writer = new FileWriter(FILENAME, true)) {
            writer.write(entryJson);
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




