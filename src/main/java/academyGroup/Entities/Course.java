package academyGroup.Entities;

import java.io.Serializable;

/**
 * The Course class represents a course offered by an academy.
 * It includes the course's ID, name, associated academy ID, and price (marked as transient).
 */
public class Course  implements Serializable {
    private int id;
    private String name;
    private int academyId;
    private transient double price;

    public Course(int id, String name, int academyId, double price) {
        this.id = id;
        this.name = name;
        this.academyId = academyId;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAcademyId() {
        return academyId;
    }

    public void setAcademyId(int academyId) {
        this.academyId = academyId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
