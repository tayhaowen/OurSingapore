package sg.edu.rp.c346.id20042303.oursingapore;

import java.io.Serializable;

public class islands implements Serializable {
    private int id;
    private String name;
    private String description;
    private int size;
    private int star;

    public islands(int id, String name, String description, int size, int star) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.star = star;
    }

    public islands(String name, String description, int size, int star) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.star = star;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
