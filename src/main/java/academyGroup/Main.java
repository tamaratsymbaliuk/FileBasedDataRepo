package academyGroup;

import academyGroup.Entities.AcademyGroup;
import academyGroup.Repository.AcademyGroupFileRepository;
import academyGroup.Repository.IRepository;

public class Main {
    public static void main(String[] args) {
        IRepository<AcademyGroup> academyGroupRepository = new AcademyGroupFileRepository("group.file");
        academyGroupRepository.add(new AcademyGroup(0, "group2024"));
        academyGroupRepository.add(new AcademyGroup(1, "group2025"));

        academyGroupRepository.update(new AcademyGroup(0, "group deprecated"));
        academyGroupRepository.remove(0);

        for (AcademyGroup academyGroup: academyGroupRepository.getAll()) {
            System.out.println("id= " + academyGroup.getId() + "; name= " + academyGroup.getName());
        }
    }
}
