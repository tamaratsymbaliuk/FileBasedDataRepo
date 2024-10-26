package academyGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteAheadLog<T> {
    // Write-ahead log
    private final String FILENAME;

    public WriteAheadLog(String fileName) {
        this.FILENAME = fileName;
    }

    public void logOperation(OperationType type, String entityName, T entity) {
        WALItem<T> walItem = new WALItem<>(type, entityName, entity);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILENAME, true))) {
            outputStream.writeObject(walItem);
            System.out.println("Operation logged: " + walItem);
        } catch (IOException e) {
            System.err.println("Failed to log operation: " + e.getMessage());

        }
    }

    public List<WALItem<T>> getAll() {
        List<WALItem<T>> walItems = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            while (true) {
                try {
                    WALItem<T> walItem = (WALItem<T>) inputStream.readObject();
                    walItems.add(walItem);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to read log: " + e.getMessage());

            }
           return walItems;
        }

    }
}




