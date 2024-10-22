package academyGroup.Repository;

import academyGroup.Entities.Academy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The AcademyRepository class manages CRUD operations for Academy entities.
 * This class interacts with a DbContext, which loads and saves data to a file-based database.
 */
public class AcademyRepository implements IRepository<Academy> {
    private DbContext context;

    /**
     * Constructor to initialize the repository with a DbContext.
     * The DbContext is responsible for interacting with the database file.
     *
     * @param context The DbContext that manages file-based database operations.
     */
    public AcademyRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public Map<String, Academy> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return new HashMap<String, Academy>(dbSet.getAcademies());
    }

    @Override
    public Academy getById(String id) {
        Map<String, Academy> academies = getAll(); // Use Map's get method for O(1) lookup
        if (academies.containsKey(id)) {
            return academies.get(id);
        }

        return null;
    }

    @Override
    public String add(Academy academy) {
        DbSet dbSet = context.getDatabaseFromFile();
        String id = UUID.randomUUID().toString();
        HashMap<String, Academy> academies = new HashMap<>(dbSet.getAcademies());

        academies.put(id, academy);

        dbSet.setAcademies(academies);
        context.SaveChangesToFile(dbSet);

        return id;
    }

    @Override
    public void update(Academy academy) {
        DbSet dbSet = context.getDatabaseFromFile();
        HashMap<String, Academy> academies = new HashMap<String,Academy>(dbSet.getAcademies());

        academies.put(academy.getId(), academy);

        dbSet.setAcademies(academies);
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(String id) {
        DbSet dbSet = context.getDatabaseFromFile();
        HashMap<String, Academy> academies = new HashMap<String,Academy>(dbSet.getAcademies());

        academies.remove(id);

        dbSet.setAcademies(academies);
        context.SaveChangesToFile(dbSet);
    }
}
