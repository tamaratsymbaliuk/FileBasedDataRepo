package homework;

import homework.Entities.Student;
import homework.Repository.StudentFileRepository;

public class Main {
    public static void main(String[] args) {
        StudentFileRepository studentRepo = new StudentFileRepository("students.dat");

        // Add new students
        studentRepo.add(new Student(1, "Alice", 20));
        studentRepo.add(new Student(2, "Bob", 22));

        // Retrieve all students
        System.out.println("All Students: " + studentRepo.getAll());

        // Update a student
        Student updatedStudent = new Student(2, "Bob", 23);
        studentRepo.update(updatedStudent);

        // Get a student by ID
        System.out.println("Student with ID 2: " + studentRepo.getById(2));

        // Remove a student
        studentRepo.remove(1);
        System.out.println("All Students after removal: " + studentRepo.getAll());
    }
}
