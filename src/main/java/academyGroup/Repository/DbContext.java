package academyGroup.Repository;

import java.io.*;

// DbContext context = new DbContext();
// DbSet database = context.getDatabaseFromFile();
// database.setMentors(updatedMentors);
// database.setCourse(updatedCourses);
// context.saveChangesToFile(database);

/**
 * Handles file-based database context, managing read and write operations for DbSet.
 * Acts as a bridge between the application and the database.
 * It manages the lifecycle of the DbSet and keeps track of changes made to entities.
 * It retrieves and saves DbSet instances, allowing for interaction with the collections of entities.
 * It handles loading data from the database file and saving changes back to it.
 * It maintains a state for tracking whether there are unsaved changes in the DbSet.
 */
public class DbContext {
    private final String FILENAME;
    private boolean containsNewChanges; // it should be from global resource file if many people use it
    private DbSet currentDbSet;

    public DbContext(String filename) {
        FILENAME = filename;
        createFileIfNew();
        containsNewChanges = true;
    }

    public boolean isContainsNewChanges() {
        return containsNewChanges;
    }

    public void setContainsNewChanges(boolean containsNewChanges) {
        this.containsNewChanges = containsNewChanges;
    }

    /**
     * Retrieves the database from file, loading it into memory.
     */
    public DbSet getDatabaseFromFile() {
        if (containsNewChanges) {
            currentDbSet = new DbSet();

            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                currentDbSet = (DbSet) objectInputStream.readObject();
            } catch (EOFException e) {

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            containsNewChanges = false;
            return currentDbSet;
        }
        return currentDbSet;
    }

    /**
     * Saves the current database state to file.
     */
    public void SaveChangesToFile(DbSet dbSet) {
        containsNewChanges = true;
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            objectOutputStream.writeObject(dbSet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
