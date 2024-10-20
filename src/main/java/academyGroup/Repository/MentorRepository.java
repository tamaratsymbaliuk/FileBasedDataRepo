package academyGroup.Repository;

import academyGroup.Entities.Mentor;
import java.util.List;
import java.util.Map;

/**
 * Repository for managing CRUD operations for Mentor entities.
 */
public class MentorRepository implements IRepository<Mentor> {
    private DbContext context;
    public MentorRepository(DbContext context) {
        this.context = context;
    }


    @Override
    public Map<Integer, Mentor> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getMentors();
    }

    @Override
    public Mentor getById(int id) {
        return getAll().get(id);  // O(1) lookup by Map key
    }

    @Override
    public void add(Mentor mentor) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addMentor(mentor);  // Add the group using the DbSet method
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void update(Mentor mentor) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.addMentor(mentor);  // Replace if exists
        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(int id) {
        DbSet dbSet = context.getDatabaseFromFile();
        dbSet.getMentors().remove(id);  // Remove by key in the Map
        context.SaveChangesToFile(dbSet);
    }
    public List<Mentor> getMentorsByAcademyId(int academyId) {
        DbSet dbSet = context.getDatabaseFromFile();
        return dbSet.getMentorsByAcademyId(academyId);
    }
}