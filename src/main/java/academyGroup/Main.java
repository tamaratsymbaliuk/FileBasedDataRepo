package academyGroup;

import academyGroup.Entities.Academy;
import academyGroup.Entities.Course;
import academyGroup.Entities.Group;
import academyGroup.Entities.Mentor;
import academyGroup.Repository.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String fileName = "db.file";
        //DbBackup dbBackup = new DbBackup(fileName);
        //WriteAheadLog wal = new WriteAheadLog("wal.log");
        //dbBackup.RunEvery(30,0);
        FillData(fileName);
        PrintData(fileName);
        System.in.read();
    }
    private static void FillData(String fileName) {
        DbContext database = new DbContext(fileName);
        AcademyRepository academyRepository = new AcademyRepository(database);
        GroupRepository academyGroupRepository = new GroupRepository(database);
        CourseRepository courseRepository = new CourseRepository(database);
        MentorRepository mentorRepository = new MentorRepository(database);

        Academy academy = new Academy("GrowthHungry Academy - education for everyone");
        String academyId = academyRepository.add(academy);
        System.out.println(academyId);

        Group group2024 = new Group("group 2024", academyId);
        String group2024Id = academyGroupRepository.add(group2024);
        Group group2025 = new Group("group 2025", academyId);
        String group2025Id = academyGroupRepository.add(group2025);

        Course csCourse = new Course("Computer Science", academyId, 24.00);
        String courseId = courseRepository.add(csCourse);
        Course algoCourse = new Course("Algorithms", academyId, 24.00);
        String algoCourseId = courseRepository.add(algoCourse);

        Mentor nurbekMentor = new Mentor("Nurbek Akparaliev", academyId);
        String nurbekMentorId = mentorRepository.add(nurbekMentor);
        Mentor dastanMentor = new Mentor("Dastan Telnov", academyId);
        String dastanMentorId = mentorRepository.add(dastanMentor);
        Mentor scottMentor = new Mentor("Scott Miles", academyId);
        String scottMentorId = mentorRepository.add(scottMentor);
        //segmentMentorA-D {nurbekMentor, dastanMentor}
        //segmentMentorF-Z {scottMentor}
        // B-Tree
        //                          root
        //  segmentMentorA-D[Updated]   segmentMentorF-Z
        // sMA, sMB[Updated], smC
    }

    private static void PrintData(String fileName) {
        DbContext database = new DbContext(fileName);
        AcademyRepository academyRepository = new AcademyRepository(database);
        GroupRepository academyGroupRepository = new GroupRepository(database);
        CourseRepository courseRepository = new CourseRepository(database);
        MentorRepository mentorRepository = new MentorRepository(database);

        System.out.println("All Academies information: ");
        for (Academy academy : academyRepository.getAll().values()) {
            System.out.println(academy.getDescription());
        }

        System.out.println("All groups names: ");
        for (Group academyGroup : academyGroupRepository.getAll().values()) {
            System.out.println(academyGroup.getName());
        }

        System.out.println("All courses: ");
        for (Course course : courseRepository.getAll().values()) {
            System.out.println(course.getName());
        }

        System.out.println("All mentors: ");
        for (Mentor mentor : mentorRepository.getAll().values()) {
            System.out.println(mentor.getName());
        }
    }
}
