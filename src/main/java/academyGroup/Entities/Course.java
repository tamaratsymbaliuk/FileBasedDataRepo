package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Course class represents a course offered by an academy.
 * It includes the course's ID, name, associated academy ID, and price (marked as transient).
 */
public class Course  implements Serializable {
    private String id;
    private String name;
    private String academyId;
    private transient double price;

    public Course(String name, String academyId, double price) {
        this.name = name;
        this.academyId = academyId;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAcademyId() {
        return academyId;
    }

    public void setAcademyId(String academyId) {
        this.academyId = academyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
