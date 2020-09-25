package org.elective.model.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

@Table(name = "subjects", fieldsAutoNaming = true)
public class Subject {
    @TableField(type = TableField.TF_GENERATED)
    private int id;
    private String nameEN;
    private String nameUA;
    private String backgroundFile;

    public Subject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getBackgroundFile() {
        return backgroundFile;
    }

    public void setBackgroundFile(String backgroundFile) {
        this.backgroundFile = backgroundFile;
    }
}
