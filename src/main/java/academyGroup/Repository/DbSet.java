package academyGroup.Repository;

import academyGroup.Entities.*;

import java.io.Serializable;
import java.util.*;
//import javafx.util.Pair;

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
    private Map<String, Academy> academies; // vs ArrayList<Academy> academies
    private Map<String, Mentor> mentors; //  id(integer) is clustered index
    private Map<String, Course> courses;
    private Map<String, Group> groups;
    private Map<String, MentorsToCourses> mentorsToCourses;

    // DbSet database = new DbSet
    // List<Mentor> mentors = databases.getMentors();
    // mentors.add(new Mentor("Toma"));
    // loose ownership because it returns a reference to the internal list mentors without creating a copy of it.

    // secondary indexes - non-clustered indexes (based on foreign keys)
    // academyId is non clustered index, and in set we store indices of groups
    private Map<String, Set<String>> groupsAcademyIndex; // Academy to Groups
    private Map<String, Set<String>> coursesAcademyIndex; // Academy to Courses
    private Map<String, Set<String>> mentorsAcademyIndex; // Mentor to Courses

    private Map<Pair<String, String>, Set<String>> courseGroupIndex;
    private Map<Pair<String, String>, Set<String>> mentorGroupIndex;

    public DbSet() {
        academies = new HashMap<>();
        groups = new HashMap<>();
        mentors = new HashMap<>();
        courses = new HashMap<>();
        mentorsToCourses = new HashMap<>();

        groupsAcademyIndex = new TreeMap<>();
        coursesAcademyIndex = new TreeMap<>();
        mentorsAcademyIndex = new TreeMap<>();
        courseGroupIndex = new TreeMap<>();
        mentorGroupIndex = new TreeMap<>();

    }

    // To avoid losing ownership of private lists, we return a copy of the lists
    // *** Clustered Indexes ***
// Academy
    public Map<String,Academy> getAcademies() {
        return new HashMap<>(academies);
    }
    public void setAcademies(Map<String,Academy> academies) {
        this.academies = academies;
    }

    // Group
    public Map<String,Group> getGroups() {
        return new HashMap<>(groups);
    }
    public void setGroups(Map<String,Group> groups) {
        this.groups = groups;
    }
    public Set<String> getGroupAcademyIndex(String academyId) {
        if (this.groupsAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.groupsAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }

    public void setGroupAcademyIndex(String academyId, Set<String> groups) {
        this.groupsAcademyIndex.put(academyId, groups);
    }
    public List<Group> getGroupsByAcademyId(String academyId) {
        List<Group> result = new ArrayList<>();
        Set<String> indices = groupsAcademyIndex.get(academyId);
        if (indices != null) {
            for (String index : indices) {
                result.add(groups.get(index));
            }
        }
        return result;
    }

    // Course
    public Map<String,Course> getCourses() {
        return new HashMap<>(courses);
    }
    public void setCourses(Map<String,Course> courses) {
        this.courses = courses;
    }
    public Set<String> getCoursesAcademyIndex(String academyId) {
        if (this.coursesAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.coursesAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }
    public void setCoursesAcademyIndex(String academyId, Set<String> academyGroups) {
        this.coursesAcademyIndex.put(academyId, academyGroups);
    }
    public List<Course> getCoursesByAcademyId(String academyId) {
        List<Course> result = new ArrayList<>();
        Set<String> indices = coursesAcademyIndex.get(academyId);
        if (indices != null) {
            for (String index : indices) {
                result.add(courses.get(index));
            }
        }
        return result;
    }

    // Mentor
    public Map<String,Mentor> getMentors() {
        return new HashMap<>(mentors);
    }
    public void setMentors(Map<String,Mentor> mentors) {
        this.mentors = mentors;
    }
    public Set<String> getMentorsAcademyIndex(String academyId) {
        if (this.mentorsAcademyIndex.containsKey(academyId)) {
            return new HashSet<>(this.mentorsAcademyIndex.get(academyId));
        }

        return new HashSet<>();
    }
    public void setMentorsAcademyIndex(String academyId, Set<String> academyGroups) {
        this.mentorsAcademyIndex.put(academyId, academyGroups);
    }
    public List<Mentor> getMentorsByAcademyId(String academyId) {
        List<Mentor> result = new ArrayList<>();
        Set<String> indices = mentorsAcademyIndex.get(academyId);
        if (indices != null) {
            for (String index : indices) {
                result.add(mentors.get(index));
            }
        }
        return result;
    }

    // MentorsToCourses
    public Map<String,MentorsToCourses> getMentorsToCourses() {
        return new HashMap<>(mentorsToCourses);
    }
    public void setMentorsToCourses(Map<String,MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }
    public Set<String> getMentorsToCoursesCourseGroupIndex(String courseId, String groupId) {
        return new HashSet<>(this.courseGroupIndex.get(new Pair<>(courseId,groupId)));
    }
    public void setMentorsToCoursesCourseGroupIndex(String courseId, String groupId, Set<String> mentorsToCourses) {
        this.courseGroupIndex.put(new Pair<>(courseId,groupId), mentorsToCourses);
    }
    public Set<String> getMentorsToCoursesMentorGroupIndex(String mentorId, String groupId) {
        return new HashSet<>(this.mentorGroupIndex.get(new Pair<>(mentorId, groupId)));
    }
    public void setMentorsToCoursesMentorGroupIndex(String mentorId, String groupId, Set<String> mentorsToCourses) {
        this.mentorGroupIndex.put(new Pair<>(mentorId, groupId), mentorsToCourses);
    }

    // give me all mentors names in CS course and in group 2024
    // result: Nurbek, Scott
    // process: courseGroupIndex contains pair of courseId, groupId as a key, a value it is set of mentorsToCourses(id, courseId, groupId, mentorId)
    public List<Mentor> getAllMentorsByCourseIdAndGroupId(String courseId, String groupId) {
        List<Mentor> result = new ArrayList<>();
        Set<String> indices = courseGroupIndex.get(new Pair<>(courseId, groupId));
        if (indices != null) {
            for (String index : indices) {
                MentorsToCourses mentorsToCourses = this.mentorsToCourses.get(index);
                result.add(mentors.get(mentorsToCourses.getMentorId()));
            }
        }
        return result;
    }

    // give me all courses where this mentor teaches and group id is {groupId} , Azret + group2024
    // result: Algorithms, Math
    // process: mentorGroupIndex contains pair of mentorId, groupId as a key, a value it is set of mentorsToCourses(id, courseId, groupId, mentorId)
    public List<Course> getAllCoursesByMentorIdAndGroupId(String mentorId, String groupId) {
        List<Course> result = new ArrayList<>();
        Set<String> indices = mentorGroupIndex.get(new Pair<>(mentorId, groupId));
        if (indices != null) {
            for (String index : indices) {
                MentorsToCourses mentorsToCourses = this.mentorsToCourses.get(index);
                result.add(courses.get(mentorsToCourses.getCourseId()));
            }
        }
        return result;
    }
}


    // *** Non-clustered Index Methods ***
//   SELECT groupName FROM Group WHERE AcademyId=1?
