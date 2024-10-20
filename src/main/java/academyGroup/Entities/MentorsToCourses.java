package academyGroup.Entities;

import java.io.Serializable;

/**
 * The MentorsToCourses class represents a many-to-many relationship between mentors and courses.
 * It includes the relationship's ID, course ID, mentor ID, and group ID.
 */
public class MentorsToCourses implements Serializable {
    // many-to-many relationship
    private int id;
    private int courseId;
    private int mentorId;
    private int groupId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
