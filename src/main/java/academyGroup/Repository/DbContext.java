package academyGroup.Repository;

import academyGroup.Entities.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// DbContext context = new DbContext();
// DbSet database = context.getDatabaseFromFile();
// database.setMentors(updatedMentors);
// database.setCourse(updatedCourses);
// context.saveChangesToFile(database);
public class DbContext {
    private final String FILENAME;
    private boolean containsNewChanges; // it should be from global resource file if many people use it
    private DbSet currentDbSet;

    public DbContext(String filename) {
        FILENAME = filename;
        createFileIfDoesExist();
        containsNewChanges = true;
    }

    public boolean isContainsNewChanges() {
        return containsNewChanges;
    }

    public void setContainsNewChanges(boolean containsNewChanges) {
        this.containsNewChanges = containsNewChanges;
    }

    public DbSet getDatabaseFromFile() {
        if (containsNewChanges) {
            currentDbSet = new DbSet();

            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
                currentDbSet = (DbSet) objectInputStream.readObject();
            } catch (EOFException e) {

            } catch (Exception e) {
                e.printStackTrace();
            }

            containsNewChanges = false;
            return currentDbSet;
        }
        return currentDbSet;
    }

    public void SaveChangesToFile(DbSet dbSet) {
        containsNewChanges = true;
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            objectOutputStream.writeObject(dbSet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void createFileIfDoesExist() {
        File file = new File(FILENAME);
        try {
            if (file.exists()) {
                System.out.println(FILENAME + " exists");
            } else {
                file.createNewFile();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
