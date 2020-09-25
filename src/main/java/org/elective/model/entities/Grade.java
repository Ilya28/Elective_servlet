package org.elective.model.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

@Table(name = "grade_log", fieldsAutoNaming = true)
public class Grade {
    private int topic;  // PK FK
    private  int user;  // PK FK
    @TableField(name = "grade")
    private int gradeValue;

    public Grade() {
    }

    public int getTopic() {
        return topic;
    }

    public void setTopic(int topic) {
        this.topic = topic;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }
}
