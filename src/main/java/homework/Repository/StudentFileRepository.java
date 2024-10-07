package homework.Repository;

import academyGroup.Repository.IRepository;
import homework.Entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentFileRepository implements IRepository<Student> {
    private final String FILENAME;

    public StudentFileRepository(String fileName){
        this.FILENAME = fileName;
        createFileIfDoesExist();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            students = (List<Student>) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student getById(int id) {
        List<Student> students = getAll();
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void add(Student student) {
        List<Student> students = getAll();
        students.add(student);
        saveChanges(students);
    }

    @Override
    public void update(Student student) {
        List<Student> students = getAll();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                students.set(i, student);
                break;
            }
        }
        saveChanges(students);
    }

    @Override
    public void remove(int id) {
        List<Student> students = getAll();
        students.removeIf(student -> student.getId() == id);
        saveChanges(students);
    }

    private void saveChanges(List<Student> students) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createFileIfDoesExist() {
        File file = new File(FILENAME);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
