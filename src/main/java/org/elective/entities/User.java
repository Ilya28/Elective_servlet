package org.elective.entities;

import org.elective.dbtools.annotations.Table;
import org.elective.dbtools.annotations.TableField;

@Table(name = "usr", fieldsAutoNaming = true)
public class User {
    @TableField(type = TableField.TF_GENERATED)
    Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private boolean blocked;
    private String language;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public static Builder toBuilder() {
        return new Builder();
    }
    public static class Builder {
        User newUser;
        private Builder() {
            newUser = new User();
        }
        public Builder id(Long newId) {
            newUser.id = newId;
            return this;
        }
        public Builder name(String newName) {
            newUser.name = newName;
            return this;
        }
        public Builder email(String newEmail) {
            newUser.email = newEmail;
            return this;
        }
        public Builder password(String newPassword) {
            newUser.password = newPassword;
            return this;
        }
        public Builder role(String newRole) {
            newUser.role = newRole;
            return this;
        }
        public Builder blocked(boolean newBlocked) {
            newUser.blocked = newBlocked;
            return this;
        }
        public Builder language(String newLanguage) {
            newUser.language = newLanguage;
            return this;
        }
        public User build(){
            return newUser;
        }
    }

}
