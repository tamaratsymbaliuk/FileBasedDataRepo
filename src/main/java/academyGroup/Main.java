package academyGroup;

import academyGroup.Entities.Academy;
import academyGroup.Repository.AcademyRepository;
import academyGroup.Repository.DbContext;
import academyGroup.Repository.IRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        DbContext context = new DbContext("db");
        IRepository<Academy> academyRepository = new AcademyRepository(context);
      //  academyRepository.add(new Academy(0, "GrowthHungry Academy"));
        for (Academy academy : academyRepository.getAll()) {
            System.out.println(academy.getId() + " " + academy.getDescription());

        }


    }
}
