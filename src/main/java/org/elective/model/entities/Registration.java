package org.elective.model.entities;

import org.elective.dbtools.annotations.Table;

@Table(name = "grade_log", fieldsAutoNaming = true)
public class Registration {
    private int course;     // PK FK
    private int user;       // PK FK
    private int progress;
    private int grade;

    public Registration() {
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
