package org.elective.entities;

import org.elective.dbtools.annotations.Table;

@Table(name = "registrations", fieldsAutoNaming = true)
public class Registration {
    private Long id;
    private Long course;
    private Long user;
    private int progress;
    private int grade;

    public Registration() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourse() {
        return course;
    }

    public void setCourse(Long course) {
        this.course = course;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
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

    public static Builder toBuilder() {
        return new Builder();
    }
    public static class Builder {
        private final Registration newRegistration;
        public Builder() {
            newRegistration = new Registration();
        }
        public Builder id(Long newId) {
            newRegistration.id = newId;
            return this;
        }
        public Builder course(Long newCourse) {
            newRegistration.course = newCourse;
            return this;
        }
        public Builder user(Long newUser) {
            newRegistration.user = newUser;
            return this;
        }
        public Builder progress(int newProgress) {
            newRegistration.progress = newProgress;
            return this;
        }
        public Builder grade(int newGrade) {
            newRegistration.grade = newGrade;
            return this;
        }
        public Registration build(){
            return newRegistration;
        }
    }
}
