package academyGroup.Entities;

import java.io.Serializable;

/**
 * The MentorsToCourses class represents a many-to-many relationship between mentors and courses.
 * It includes the relationship's ID, course ID, mentor ID, and group ID.
 */
public class MentorsToCourses implements Serializable {
    // many-to-many relationship
    private String id;
    private String courseId;
    private String mentorId;
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getMentorId() {
        return mentorId;
    }

    public void setMentorId(String mentorId) {
        this.mentorId = mentorId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
