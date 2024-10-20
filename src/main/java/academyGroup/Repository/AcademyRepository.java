package academyGroup.Repository;

import academyGroup.Entities.Academy;
import java.util.Map;

/**
 * The AcademyRepository class manages CRUD operations for Academy entities.
 * It uses a DbSet to store and retrieve academy data.
 */
public class AcademyRepository implements IRepository<Academy> {
    private DbContext context;

    public AcademyRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public Map<Integer, Academy> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getAcademies();
    }

    @Override
    public Academy getById(int id) {
        return getAll().get(id);  // Use Map's get method for O(1) lookup
    }

    @Override
    public void add(Academy academy) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addAcademy(academy);  // Add directly using the DbSet method
        context.SaveChangesToFile(dbSet);  // Save changes
    }

    private void saveChanges(Map<Integer, Academy> academies) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.setAcademies(academies);
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void update(Academy academy) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addAcademy(academy);  // If academy already exists, overwrite it
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(int id) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.getAcademies().remove(id);  // Remove by key in the Map
        context.SaveChangesToFile(dbSet);
    }
}
