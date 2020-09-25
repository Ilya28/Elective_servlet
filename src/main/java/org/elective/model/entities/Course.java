package org.elective.model.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

import java.sql.Date;

@Table(name = "courses", fieldsAutoNaming = true)
public class Course {
    @TableField(type = TableField.TF_GENERATED)
    private int id;
    private String nameEN;
    private String nameUA;
    private String descriptionEN;
    private String descriptionUA;
    private int subject;            // Foreign key
    private String backgroundFile;
    private Date dateStart;
    private Date dateEnd;
    private int seats;
    private int signedUp;

    public Course() {
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

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getBackgroundFile() {
        return backgroundFile;
    }

    public void setBackgroundFile(String backgroundFile) {
        this.backgroundFile = backgroundFile;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
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
}
