package academyGroup.Repository;

import academyGroup.Entities.Mentor;

import java.util.*;

/**
 * Repository for managing CRUD operations for Mentor entities.
 */
public class MentorRepository implements IRepository<Mentor> {
    DbContext context;
    public MentorRepository(DbContext context) {
        this.context = context;
    }
    @Override
    public Map<String,Mentor> getAll() {
        DbSet dbSet = context.getDatabaseFromFile();
        return new HashMap<String,Mentor>(dbSet.getMentors());
    }

    @Override
    public Mentor getById(String id) {
        Map<String,Mentor> mentors = getAll();
        if (mentors.containsKey(id)) {
            return mentors.get(id);
        }

        return null;
    }

    @Override
    public String add(Mentor mentor) {
        DbSet dbSet = context.getDatabaseFromFile();

        // add to Mentor table
        String id = UUID.randomUUID().toString();
        mentor.setId(id); // generate new UUID random string and set it for Id of an entity
        Map<String,Mentor> mentors = new HashMap<String,Mentor>(dbSet.getMentors());
        mentors.put(mentor.getId(), mentor);
        dbSet.setMentors(mentors);

        // update academyId index
        Set<String> indices = dbSet.getMentorsAcademyIndex(mentor.getAcademyId());
        indices.add(id);
        dbSet.setMentorsAcademyIndex(mentor.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);

        return id;
    }

    @Override
    public void update(Mentor mentor) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Update Mentor table
        Map<String,Mentor> mentors = new HashMap<String,Mentor>(dbSet.getMentors());
        mentors.put(mentor.getId(), mentor);
        dbSet.setMentors(mentors);

        // update academyId index
        Set<String> indices = dbSet.getMentorsAcademyIndex(mentor.getAcademyId());
        indices.add(mentor.getId());
        dbSet.setMentorsAcademyIndex(mentor.getAcademyId(), indices);

        context.SaveChangesToFile(dbSet);
    }

    @Override
    public void remove(String id) {
        DbSet dbSet = context.getDatabaseFromFile();

        // Remove from Mentor table
        Map<String,Mentor> mentors = new HashMap<String,Mentor>(dbSet.getMentors());
        String academyId = mentors.get(id).getAcademyId();
        mentors.remove(id);
        dbSet.setMentors(mentors);

        // remove from index
        Set<String> indices = dbSet.getMentorsAcademyIndex(academyId);
        indices.remove(id);
        dbSet.setMentorsAcademyIndex(academyId, indices);

        context.SaveChangesToFile(dbSet);
    }
}

