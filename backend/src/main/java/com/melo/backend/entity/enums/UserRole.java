package com.melo.backend.entity.enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    @SuppressWarnings("FieldMayBeFinal")
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
