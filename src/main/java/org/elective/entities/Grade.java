package org.elective.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

@Table(name = "grade_log", fieldsAutoNaming = true)
public class Grade {
    private Long topic;  // PK FK
    private Long user;  // PK FK
    @TableField(name = "grade")
    private int gradeValue;

    public Grade() {
    }

    public Long getTopic() {
        return topic;
    }

    public void setTopic(Long topic) {
        this.topic = topic;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }
}
