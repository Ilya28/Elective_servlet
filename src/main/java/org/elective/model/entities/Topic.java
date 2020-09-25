package org.elective.model.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

@Table(name = "topics", fieldsAutoNaming = true)
public class Topic {
    @TableField(type = TableField.TF_GENERATED)
    private int id;
    private int course;     // Foreign key
    private String nameEN;
    private String nameUA;

    public Topic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getNameEN() {
        return nameEN;
    }

    public void setNameEN(String nameEN) {
        this.nameEN = nameEN;
    }

    public String getNameUA() {
        return nameUA;
    }

    public void setNameUA(String nameUA) {
        this.nameUA = nameUA;
    }
}
