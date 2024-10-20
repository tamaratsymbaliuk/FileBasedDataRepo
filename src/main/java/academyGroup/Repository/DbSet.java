package academyGroup.Repository;

import academyGroup.Entities.*;

import java.io.Serializable;
import java.util.*;

/**
 * Represents the in-memory database set with primary and secondary indexes.
 * DbSet acts as a collection that represents a set of entities (like Academy, Mentor, Course, etc.) in a database.
 * Each DbSet corresponds to a table in the database.
 * It provides methods for adding, retrieving, updating, and deleting entities.
 * It holds entities in a format that allows for efficient lookups and management (e.g., using a Map for quick access by ID).
 */
public class DbSet implements Serializable { // Serializable allows Java to serialize the object, meaning it can convert the object into a format that can be saved (e.g., to a file)
    //database set
    // Clustered indexes (Primary IDs stored in Maps)
    private Map<Integer, Academy> academies; // vs ArrayList<Academy> academies
    private Map<Integer, Mentor> mentors; //  id(integer) is clustered index
    private Map<Integer, Course> courses;
    private Map<Integer, Group> groups;
    private Map<Integer, MentorsToCourses> mentorsToCourses;

    // DbSet database = new DbSet
    // List<Mentor> mentors = databases.getMentors();
    // mentors.add(new Mentor("Toma"));
    // loose ownership because it returns a reference to the internal list mentors without creating a copy of it.

    // secondary indexes - non-clustered indexes (based on foreign keys)
    // academyId is non clustered index, and in set we store indices of groups
    private Map<Integer, Set<Integer>> academyGroupAcademyIndex; // Academy to Groups
    private Map<Integer, Set<Integer>> academyCourseAcademyIndex; // Academy to Courses
    private Map<Integer, Set<Integer>> mentorCoursesMentorIndex; // Mentor to Courses

    public DbSet() {
        academies = new HashMap<>();
        mentors = new HashMap<>();
        courses = new HashMap<>();
        mentorsToCourses = new HashMap<>();

        academyGroupAcademyIndex = new TreeMap<>();
        academyCourseAcademyIndex = new TreeMap<>();
        mentorCoursesMentorIndex = new TreeMap<>();
    }

    // To avoid losing ownership of private lists, we return a copy of the lists
    // *** Clustered Indexes ***
    public Map<Integer, Academy> getAcademies() {
        return new HashMap<>(academies);
    }

    public void setAcademies(Map<Integer,Academy> academies) {
        this.academies = academies;
    }
    public void addAcademy(Academy newAcademy) {
        academies.put(newAcademy.getId(),newAcademy);
    }
    public Academy getAcademy(int id) {
        return academies.get(id); // O(1)
    }

//    public Academy getacademyLegacy(int id) { O(n)
//        for (int i = 0; i < academies.size(); i++) {
//            if (academies[i].getId() == id) {
//                return academies[i];
//            }
//        }
//        return null;
//    }
    public Map<Integer,Mentor> getMentors() { // ex of loose/week ownership
    //  return mentors;
    return new HashMap<>(mentors); // return a copy instead of the original list
   }
   public void addMentor(Mentor newMentor) {
        mentors.put(newMentor.getId(), newMentor);
   }
    public Map<Integer,Course> getCourses() {
        // return courses;
        return new HashMap<>(courses);
    }

    public void addCourse(Course newCourse) {
        courses.put(newCourse.getId(), newCourse);

        // Update non-clustered index
        Set<Integer> courseIds = academyCourseAcademyIndex.getOrDefault(newCourse.getAcademyId(), new HashSet<>());
        courseIds.add(newCourse.getId());
        academyCourseAcademyIndex.put(newCourse.getAcademyId(), courseIds);
    }
    public Map<Integer,Group> getGroups() {
        //return groups;
        return new HashMap<>(groups);
    }

    public void addGroup(Group newGroup) {
        groups.put(newGroup.getId(),newGroup);

        // Update non-clustered index
        Set<Integer> academyGroupIds =
                academyGroupAcademyIndex.getOrDefault(newGroup.getAcademyId(), new HashSet<>());
        academyGroupIds.add(newGroup.getId());
        academyGroupAcademyIndex.put(newGroup.getAcademyId(),academyGroupIds);
    }
    public Map<Integer,MentorsToCourses> getMentorsToCourses() {
        // return mentorsToCourses;
        return new HashMap<>(mentorsToCourses);
    }
    public void addMentorsToCourses(MentorsToCourses newMenToCour) {
        mentorsToCourses.put(newMenToCour.getId(), newMenToCour);

        // Update non-clustered index
        Set<Integer> courseIds = mentorCoursesMentorIndex.getOrDefault(newMenToCour.getMentorId(), new HashSet<>());
        courseIds.add(newMenToCour.getCourseId());
        mentorCoursesMentorIndex.put(newMenToCour.getMentorId(), courseIds);
    }

    // *** Non-clustered Index Methods ***
//   SELECT groupName FROM Group WHERE AcademyId=1?
    public List<Group> getGroupsByAcademyId(int academyId) {
        List<Group> groups = new ArrayList<>();
        Set<Integer> academyGroupsIds = academyGroupAcademyIndex.get(academyId);
        if (academyGroupsIds != null) {
            for (int academyGroupId: academyGroupsIds) {
                groups.add(groups.get(academyGroupId));
            }
        }
        return groups;
    }
    public List<Course> getCoursesByAcademyId(int academyId) {
        List<Course> courses = new ArrayList<>();
        Set<Integer> courseIds = academyCourseAcademyIndex.get(academyId);
        if (courseIds != null) {
            for (int courseId : courseIds) {
                courses.add(courses.get(courseId));
            }
        }
        return courses;
    }

    public List<Course> getCoursesByMentorId(int mentorId) {
        List<Course> courses = new ArrayList<>();
        Set<Integer> courseIds = mentorCoursesMentorIndex.get(mentorId);
        if (courseIds != null) {
            for (int courseId : courseIds) {
                courses.add(courses.get(courseId));
            }
        }
        return courses;
    }
    public List<Mentor> getMentorsByAcademyId(int academyId) {
        List<Mentor> result = new ArrayList<>();
        for (Mentor mentor : mentors.values()) {
            if (mentor.getAcademyId() == academyId) {
                result.add(mentor);
            }
        }
        return result;
    }
}



