package org.elective.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

import java.time.LocalDate;

@Table(name = "courses", fieldsAutoNaming = true)
public class Course {
    @TableField(type = TableField.TF_GENERATED)
    private Long id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private Long subject;            // Foreign key
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private int seats;
    private int signedUp;

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionUA() {
        return descriptionUA;
    }

    public void setDescriptionUA(String descriptionUA) {
        this.descriptionUA = descriptionUA;
    }

    public Long getSubject() {
        return subject;
    }

    public void setSubject(Long subject) {
        this.subject = subject;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSignedUp() {
        return signedUp;
    }

    public void setSignedUp(int signedUp) {
        this.signedUp = signedUp;
    }

    public static Builder toBuilder() {
        return new Builder();
    }
    public static class Builder {
        private final Course newCourse;
        public Builder() {
            newCourse = new Course();
        }
        public Builder id(Long newId) {
            newCourse.id = newId;
            return this;
        }
        public Builder nameEN(String newName) {
            newCourse.nameEN = newName;
            return this;
        }
        public Builder nameUA(String newName) {
            newCourse.nameUA = newName;
            return this;
        }
        public Builder descriptionEN(String newDescription) {
            newCourse.descriptionEN = newDescription;
            return this;
        }
        public Builder descriptionUA(String newDescription) {
            newCourse.descriptionUA = newDescription;
            return this;
        }
        public Builder subject(Long newSubject) {
            newCourse.subject = newSubject;
            return this;
        }
        public Builder dateStart(LocalDate newDateStart) {
            newCourse.dateStart = newDateStart;
            return this;
        }
        public Builder dateEnd(LocalDate newDateEnd) {
            newCourse.dateEnd = newDateEnd;
            return this;
        }
        public Builder seats(int newSeats) {
            newCourse.seats = newSeats;
            return this;
        }
        public Builder signedUp(int newSignedUp) {
            newCourse.signedUp = newSignedUp;
            return this;
        }
        public Course build(){
            return newCourse;
        }
    }
}
