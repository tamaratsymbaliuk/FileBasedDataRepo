package academyGroup;

import academyGroup.Entities.Academy;
import academyGroup.Entities.Group;
import academyGroup.Entities.Mentor;
import academyGroup.Repository.*;

public class Main {
    public static void main(String[] args) throws Exception {
        DbContext context = new DbContext("db");
        IRepository<Academy> academyRepository = new AcademyRepository(context);
        IRepository<Group> groupRepo = new GroupRepository(context);
        IRepository<Mentor> mentorRepo = new MentorRepository(context);

        //  academyRepository.add(new Academy(0, "GrowthHungry Academy"));
//        for (Academy academy : academyRepository.getAll()) {
//            System.out.println(academy.getId() + " " + academy.getDescription());
//        }

        // Add a new Academy
        Academy academy1 = new Academy(0, "GrowthHungry Academy");
        Academy academy2 = new Academy(1, "Innovators Academy");
        academyRepository.add(academy1);
        academyRepository.add(academy2);

        // Add new Groups to each Academy
        Group group1 = new Group(0, "Group 2023", academy1.getId());
        Group group2 = new Group(1, "Group 2024", academy1.getId());
        Group group3 = new Group(2, "Group 2025", academy2.getId());
        groupRepo.add(group1);
        groupRepo.add(group2);
        groupRepo.add(group3);

        // Add new Mentors for each Academy
        Mentor mentor1 = new Mentor(0, "John Doe", academy1.getId());
        Mentor mentor2 = new Mentor(1, "Jane Smith", academy2.getId());
        mentorRepo.add(mentor1);
        mentorRepo.add(mentor2);
       // System.out.println("Group ID: " + group.getId() + ", Name: " + group.getName() + ", Academy ID: " + group.getAcademyId());


        // Test removing an Academy (and handle its related entities if needed)

    }
}