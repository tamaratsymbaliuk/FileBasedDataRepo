package academyGroup.Repository;

import academyGroup.Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DbSet implements Serializable {
    //database set
    private List<Academy> academies;
    private List<Mentor> mentors;
    private List<Course> courses;
    private List<Group> groups;
    private List<MentorsToCourses> mentorsToCourses;

    // DbSet database = new DbSet
    // List<Mentor> mentors = databases.getMentors();
    // mentors.add(new Mentor("Toma"));
    // loose ownership because it returns a reference to the internal list mentors without creating a copy of it.


    public DbSet() {
        academies = new ArrayList<>();
        mentors = new ArrayList<>();
        courses = new ArrayList<>();
        mentorsToCourses = new ArrayList<>();
    }

    // To avoid losing ownership of private lists, we return a copy of the lists
    public List<Academy> getAcademies() {
       // return academies;
        return new ArrayList<>(academies);
    }

    public void setAcademies(List<Academy> academies) {
        this.academies = academies;
    }

    public List<Mentor> getMentors() { // ex of loose/week ownership
      //  return mentors;
        return new ArrayList<>(mentors); // return a copy instead of the original list
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<Course> getCourses() {
       // return courses;
        return new ArrayList<>(courses);
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Group> getGroups() {
        //return groups;
        return new ArrayList<>(groups);
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<MentorsToCourses> getMentorsToCourses() {
       // return mentorsToCourses;
        return new ArrayList<>(mentorsToCourses);
    }

    public void setMentorsToCourses(List<MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }
}
