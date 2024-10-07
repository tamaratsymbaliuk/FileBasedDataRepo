package academyGroup.Repository;

import academyGroup.Entities.AcademyGroup;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class AcademyGroupFileRepository implements IRepository<AcademyGroup> {
    private final String FILENAME;
    public AcademyGroupFileRepository(String fileName) {
        FILENAME = fileName;
        createFileIfDoesExist();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AcademyGroup> getAll() {
        List<AcademyGroup> academyGroups = new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            academyGroups = (List<AcademyGroup>) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch(Exception e) {
            e.printStackTrace();
        }
        return academyGroups;
    }

    @Override
    public AcademyGroup getById(int id) {
        List<AcademyGroup> academyGroups = getAll();

        for (AcademyGroup academyGroup: academyGroups) {
            if (academyGroup.getId() == id) {
                return academyGroup;
            }
        }
        return null;
    }

    @Override
    public void add(AcademyGroup academyGroup) {
        List<AcademyGroup> academyGroups = getAll(); // application heap
        academyGroups.add(academyGroup); // your changes affect only heap
        saveChanges(academyGroups); // let's update our file with data from heap
    }

    private void saveChanges(List<AcademyGroup> academyGroups) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))){
            objectOutputStream.writeObject(academyGroups);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AcademyGroup academyGroup) {
        List<AcademyGroup> academyGroups = getAll();

        for (int i = 0; i < academyGroups.size(); i++) {
            if (academyGroups.get(i).getId() == academyGroup.getId()) {
                academyGroups.set(i, academyGroup);
                break;
            }
        }
        saveChanges(academyGroups);
    }

    @Override
    public void remove(int id) {
        List<AcademyGroup> academyGroups = getAll();
        academyGroups.removeIf(group -> group.getId() == id);
        saveChanges(academyGroups);
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
