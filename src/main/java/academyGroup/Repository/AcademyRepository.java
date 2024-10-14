package academyGroup.Repository;

import academyGroup.Entities.Academy;
import academyGroup.Entities.Group;

import java.util.List;

public class AcademyRepository implements IRepository<Academy> {
    private DbContext context;

    public AcademyRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public List<Academy> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getAcademies();
    }

    @Override
    public Academy getById(int id) {
        List<Academy> academies = getAll();

        for (Academy academy : academies) {
            if (academy.getId() == id) {
                return academy;
            }
        }
        return null;
    }

    @Override
    public void add(Academy academy) {
        List<Academy> academies = getAll(); // application heap
        academies.add(academy); // your changes affect only heap
        saveChanges(academies); // let's update our file with data from heap
    }

    private void saveChanges(List<Academy> academies) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.setAcademies(academies);
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void update(Academy academy) {
        List<Academy> academies = getAll();

        for (int i = 0; i < academies.size(); i++) {
            if (academies.get(i).getId() == academy.getId()) {
                academies.set(i, academy);
                break;
            }
        }
        saveChanges(academies);
    }

    @Override
    public void remove(int id) {
        List<Academy> academies = getAll();
        academies.removeIf(group -> group.getId() == id);
        saveChanges(academies);
    }
}
