package org.example.schoolsystem.model.user;

public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    TEACHER ("ROLE_TEACHER");

    private String role;

    UserRole (String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
